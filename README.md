# Word count application

## Dependencies

Groovy

## Assumptions

* The purpose of this is to be used by an engineer locally
* The engineer has Groovy installed

## Usage

In the locally extracted archive (or checked out git repo), execute `./word_count.groovy <FILE_PATH>` (`tests/input.txt` can be used)

It can be installed as a binary, if you like, by copying it to a location in your path and removing the `.groovy` extension.  
e.g. `cp word_count.groovy /usr/bin/word-count`  
Usage would then be `word-count <FILE_PATH>`, executed from anywhere.

## Build

This is a simple single script app that requires no compilation, so there's no build to speak of but the makefile includes a step to package this repo into a tarball (`make build`).
A possible build would be for a Jenkins pipeline, or other build mechanism such as GitHub workflows/actions, to checkout the repo, execute the tests and then publish just the word_count.groovy file in whatever way is standard for the company (GitHub releases, Artifactory, etc)

## Testing

Execute `groovy tests/unit_test.groovy` (or `make test` if you do not have Groovy installed but have Docker installed)

## Notes

The Makefile was primarily for my use but I've left it in incase it's of interest or use to anyone else. e.g. usage `make run FILE_PATH=tests/input_empty.txt`

A drawio architecture diagram of some possible system deployments in AWS is included as an example of how this might be deployed as a service. For something this simple I'd expect a service like Lambda to be the best fit but without a spec the possiblities are broad. It assumes the app would be re-written to integrate into this setup.

My specialism is in containerisation but, as it is, this application wouldn't be suitable for that as the file being read would have to exist within the container. This is possible with a wrapper script but it begins to get more complicated. For this case I would expect the application to be changed to expect it's input on stdin so that the file content can just be piped to it, this was not the spec though. If it were the case then it'd be simple to make a short Dockerfile that contains Groovy, thus removing the dependence on Groovy being installed locally (and introducing the dependence on Docker being installed but that is fairly standard these days and would be needed once to support many different apps with many different dependencies at different versions)
