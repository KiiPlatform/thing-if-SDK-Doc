package main

import (
	"bufio"
	"errors"
	"fmt"
	"os"
	"path/filepath"
	"regexp"
	"strings"
	"text/template"
)

func check(e error) {
	if e != nil {
		panic(e)
	}
}

var tagReg = regexp.MustCompile(`( +)\/\/ CodeTag(Start|End): (.+)`)
var tagMap map[string]string

func parseSourceListFile(file File) error {
	f, _ := os.Open(file.FullName)
	defer f.Close()
	scanner := bufio.NewScanner(f)
	scanner.Split(bufio.ScanLines)
	for scanner.Scan() {
		aLine := scanner.Text()
		srcFile := file.Path + aLine
		if _, err := os.Stat(srcFile); os.IsNotExist(err) {
			return errors.New("srce file name: " + aLine + " described in " + file.FullName + " does not exists")
		}
		if err := parseSource(srcFile); err != nil {
			return err
		}
	}
	return nil
}

func parseSource(source string) error {
	srcFile, _ := os.Open(source)
	defer srcFile.Close()
	scanner := bufio.NewScanner(srcFile)
	scanner.Split(bufio.ScanLines)

	started := false
	var tag string
	snippet := []string{}
	var intent string
	// read line
	for scanner.Scan() {
		aLine := scanner.Text()
		m := tagReg.FindStringSubmatch(aLine)
		if m != nil {
			intent = m[1]
			if m[2] == "Start" {
				started = true
				tag = m[3]
			} else {
				started = false
				if _, ok := tagMap[tag]; ok {
					return errors.New(tag + "in file: " + source + ", which is already used in other file.")
				}
				tagMap[tag] = strings.Join(snippet, "\n")
				snippet = []string{}
			}
			continue
		}
		if started {
			// fix intent
			aLine = strings.Replace(aLine, intent, "", 1)
			snippet = append(snippet, aLine)
		}
	}
	return nil
}

func createDoc(out, tmpl string) error {

	// expand template.
	t, err := template.ParseFiles(tmpl)
	if err != nil {
		return err
	}
	f, err := os.Create(out)
	if err != nil {
		return err
	}
	defer f.Close()
	if err := t.Execute(f, tagMap); err != nil {
		return err
	}
	return nil
}

var srcReg = regexp.MustCompile(`((.+\/)*)\.source`)
var tmpleReg = regexp.MustCompile(`((.+\/)*)(.+)\.tmpl`)

type File struct {
	FullName string
	Path     string
	Name     string
}

type Document struct {
	TmplFile File
	RstFile  File
}

var srcFiles []File
var dFiles []Document

func searchFile(path string, f os.FileInfo, err error) error {
	m1 := srcReg.FindStringSubmatch(path)
	m2 := tmpleReg.FindStringSubmatch(path)
	if len(m1) != 0 {
		fmt.Printf("found source file, path: %s\n", m1[2])
		srcFiles = append(
			srcFiles,
			File{
				FullName: path,
				Path:     m1[2],
			})
		return nil
	}
	if len(m2) != 0 {
		fmt.Printf("found templetate file, path: %s, name: %s\n", m2[2], m2[3])
		dFiles = append(
			dFiles,
			Document{
				TmplFile: File{
					FullName: path,
				},
				RstFile: File{
					Path: m2[2],
					Name: m2[3],
				},
			})
	}
	return nil
}

func main() {
	// initialization
	tagMap = map[string]string{}
	err := filepath.Walk(".", searchFile)
	if err != nil {
		fmt.Printf("filepath.Walk() returned %v\n", err)
	}

	// parse source file
	for _, srcFile := range srcFiles {
		if err = parseSourceListFile(srcFile); err != nil {
			fmt.Printf("err: %s", err)
			return
		}
	}

	// generate document
	for _, doc := range dFiles {
		docFile := doc.RstFile.Path + doc.RstFile.Name
		if err = createDoc(docFile, doc.TmplFile.FullName); err != nil {
			fmt.Printf("faild to create %s, err: %s", docFile, err)
		}
	}
}
