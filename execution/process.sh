#!/bin/bash

source config.sh

function process_dir {
	./html.sh $1 > $outputDir$1/index.php
	for a in $(ls $javaDir$1 | grep -v "package-info.java"); do
		if [[ -d "$javaDir$1/$a" ]]; then
			if [[ ! -d "$outputDir$1/$a" ]]; then mkdir "$outputDir$1/$a"; fi
			process_dir "$1/$a"
		elif [[ $a != "Example.java" ]]; then
			if [[ ! -d "$outputDir$1/${a%.java}" ]]; then mkdir "$outputDir$1/${a%.java}"; fi
			process_file "$1/${a%.java}"
		fi
	done
}

function process_file {
	echo ">>> $1"
	if [[ ! -f $outputDir$1/log ]]; then
		java -jar examples.jar $inputDir $outputDir $1 &> $outputDir$1/log
	fi
	./html.sh $1 > $outputDir$1/index.php
}

process_dir ""

