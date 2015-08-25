#!/bin/bash

source config.sh

function process_dir {
	if [[ $1 != "" ]]; then
		file="$javaDir$1/package-info.java"
		if [[ ! -e "$file" ]]; then
			echo "creating: $file"
			echo "/**" > $file
			echo " *" >> $file
			echo " */" >> $file
			echo "package dna.examples$2;" >> $file
		fi
	fi
	for a in $(ls $javaDir$1); do
		if [[ -d "$javaDir$1/$a" ]]; then
			process_dir "$1/$a" "$2.$a"
		fi
	done
}

process_dir "" ""