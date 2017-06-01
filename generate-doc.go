package main

import (
	"bufio"
	"fmt"
	"os"
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

func makeSnippet(out, source, tmpl string) {

	srcFile, _ := os.Open(source)
	defer srcFile.Close()
	scanner := bufio.NewScanner(srcFile)
	scanner.Split(bufio.ScanLines)

	tagMap := map[string]string{}
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

	// expand template.
	t, err := template.ParseFiles(tmpl)
	if err != nil {
		fmt.Printf("err: %v", err)
	}
	f, err := os.Create(out)
	if err != nil {
		fmt.Printf("err: %v", err)
	}
	defer f.Close()
	if err := t.Execute(f, tagMap); err != nil {
		fmt.Printf("err: %v", err)
	}
}

type Platform struct {
	Name               string
	SourceFilePath     string
	TempletateFilePath string
	ResultFilePath     string
}

func main() {
	platforms := []Platform{
		Platform{
			Name:               "android",
			SourceFilePath:     "snippets/android/SampleProject/app/src/main/java/test/android/example/com/snippetsample/MainActivity.java",
			TempletateFilePath: "snippets/android/snippet.md.tmpl",
			ResultFilePath:     "snippets/android/snippet.md",
		},
		// add other platform files here
	}
	for _, platform := range platforms {
		makeSnippet(platform.ResultFilePath, platform.SourceFilePath, platform.TempletateFilePath)
	}
}
