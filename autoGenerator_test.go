package main

import (
	"os"
	"testing"
)

func TestAutoGenerate(t *testing.T) {
	snippet1 := "./test_folder/snippet-js.md"
	snippet2 := "./test_folder/snippet-android.md"
	if _, err := os.Stat(snippet1); os.IsExist(err) {
		os.Remove(snippet1)
	}
	if _, err := os.Stat(snippet2); os.IsExist(err) {
		os.Remove(snippet2)
	}
	autoGenerate("test_folder")

	if _, err := os.Stat(snippet1); os.IsNotExist(err) {
		t.Errorf("expected %s should be generated", snippet1)
	}
	if _, err := os.Stat(snippet2); os.IsExist(err) {
		t.Errorf("expected %s should be generated", snippet2)
	}
}
