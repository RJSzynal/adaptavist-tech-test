#!/usr/bin/env groovy

def countWords(file) {
    def wordCount = [:]

    file.eachLine { line ->
        line.split(/\W/).each { word ->
            if(word) {
                if(wordCount[word]) {
                    wordCount[word]++
                } else {
                    wordCount[word] = 1
                }
            }
        }
    }

    return wordCount
}

if(args.length != 1) {
    throw new Exception('Please provide a path to a file as an argument')
}

def file = new File(args[0])

if(!file.exists()) {
    throw new Exception('File does not exist')
}

def wordCount = countWords(file)

wordCount.sort { a, b -> b.value <=> a.value }.each {
    println "${it.key}: ${it.value}"
}

