## Requirement

- [go 1.8](https://golang.org/doc/install)

## Source code file
You can read source code by any programming language. Parts of code in the source code will be extracted and filled into document templetate file.

Describe these code using pair of start tag and end tag. The snippet code should be included between start tag and end tag.

### start tag

`// CodeTagStart: [tag_name]`

### end tag

`// CodeTagEnd: [tag_name]`


## Document template file

Template file should be name with suffix `.tmpl`. Place the tag in the file with format `{{.tag_name}}`.
`auto_generator.go` will replace the tag in template file with the code defined in source file with that `tag_name`, and generate a document, with the template name without `.tmpl` under the same folder of template file.

## Create source list file
You need one or more source list file, named `.source` to describe the list of source file.

In the `.source` file, you can list the source code file with exact name or regular expression. For example

Place the following `.source` file under path `snippets/java/`

```
a.java
p1/p2/*.java
```
`auto-generator.go` will treat `snippets/java/a.java`, and all java files under `snippets/java/p1/p2/` as source code files. `auto-generator.go` parse these source code and extract tag and snippet

## Run generation script
- go run auto_generator.go

