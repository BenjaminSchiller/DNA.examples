#!/bin/bash

source config.sh



OIFS=$IFS
IFS="/"
elements=()
for a in $1; do
	elements+=("$a")
done
IFS=$OIFS

nav=""
back=""
for (( i=${#elements[@]}-1; i>0; i-- )); do
	nav=" - <a href='${back}index.php'>${elements[$i]}</a>$nav"
	back="../$back"
done
nav="<a href='${back}index.php'>dna.example</a>$nav"
IFS=$OIFS


echo '<?php'
	# echo '$path = str_replace("/Users/benni/TUD/Projects/DNA/DNA.webpage/web/", "", getcwd());'
	echo '$path = ltrim(str_replace($_SERVER["DOCUMENT_ROOT"], "", getcwd()), "/");'
	echo '$dirs = split("/", $path);'
	echo '$pre = "";'
	echo 'for($i = 0; $i < count($dirs); $i++) { $pre .= "../"; }'
	echo 'require($pre."layout/header.php");'
echo '?>'

if [[ -f $javaDir$1.java ]]; then

	# echo "<html>"
	# echo '<head>'
	# echo '	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />'
	# echo '	<title>Hello SyntaxHighlighter</title>'
	# echo '	<script type="text/javascript" src="../../../html/syntaxhighlighter/scripts/shCore.js"></script>'
	# echo '	<script type="text/javascript" src="../../../html/syntaxhighlighter/scripts/shBrushJava.js"></script>'
	# echo '	<link type="text/css" rel="stylesheet" href="../../../html/syntaxhighlighter/styles/shCoreEclipse.css"/>'
	# echo '	<script type="text/javascript" src="../../../../html/syntaxhighlighter/scripts/shCore.js"></script>'
	# echo '	<script type="text/javascript" src="../../../../html/syntaxhighlighter/scripts/shBrushJava.js"></script>'
	# echo '	<link type="text/css" rel="stylesheet" href="../../../../html/syntaxhighlighter/styles/shCoreEclipse.css"/>'
	# echo '	<script type="text/javascript">SyntaxHighlighter.all();</script>'
	# echo '</head>'

	#echo "<body>$nav<br></br>"
	# echo "<h1>${elements[${#elements[@]} - 1]}</h1>"

	#echo '<table><tr><td colspan="2">'
	echo "<h2>DESCRIPTION</h2>"
	echo '<pre style="background-color: #f0f0f0; border-style:dashed; border-width: 1px;">'
	# cat $javaDir$1.java | grep "\*" | grep -v "/\*" | grep -v "\*/" | grep -v "@author" | sed 's/ \* //g' | sed 's/ \*//'
	awk '/\/\*\*/,/\*\//' $javaDir$1.java | grep -v "/\*\*" | grep -v "\*/" | grep -v "@author" | sed 's/ \* //g' | sed 's/ \*//'
	echo "</pre>"
	#echo '</td></tr><tr><td>'
	echo "<h2>CODE</h2>"
	echo '<pre style="height: auto; max-height: 300px; overflow: auto; border-style:dashed; border-width: 1px;">'
	echo '<pre class="brush: java;">'
	cat $javaDir$1.java | grep -v "\*" | sed -e '1,/ class /d' | sed \$d | sed 's/	//'
	echo "</pre>"
	echo "</pre>"
	echo '</td><td>'

	if [[ -f $outputDir$1/log ]]; then
		echo "<h2>LOG OUTPUT</h2>"
		echo '<pre style="height: auto; max-height: 300px; overflow: auto; background-color: #f0f0f0; border-style:dashed; border-width: 1px;">'
		cat $outputDir$1/log
		echo "</pre>"
	fi

	if [[ -d $outputDir$1/$dataDir/ ]]; then
		echo "<h2>DATA</h2>"
		echo "<ul>"
		for a in $(ls $outputDir$1/$dataDir); do
			echo "<li> <a href='$dataDir/$a'>$a</a> </li>"
		done
		echo "</ul>"
	fi

	if [[ -d $outputDir$1/$plotDir/ ]]; then
		echo "<h2>PLOTS</h2>"
		for a in $(ls $outputDir$1/$plotDir/ | grep '.png\|.pdf'); do
			echo "<a href='$plotDir/$a'><img src='$plotDir/$a' width='200'/></a>"
		done
	fi

	if [[ -d $outputDir$1/$screenshotDir/ ]]; then
		echo "<h2>SCREENSHOTS</h2>"
		for a in $(ls $outputDir$1/$screenshotDir/ | grep 'png'); do
			echo "<a href='$shapshotDir/$a'><img src='$screenshotDir/$a' title='$a' width='$screenshotWidth'/></a>"
		done
		for a in $(ls $outputDir$1/$screenshotDir/ | grep -v '.png\|.pdf'); do
			if [[ -d $outputDir$1/$screenshotDir/$a ]]; then
				if [[ -f $outputDir$1/$screenshotDir/$a/$animatedFilename ]]; then
					echo "<a href='$shapshotDir/$a/$animatedFilename'><img src='$screenshotDir/$a/$animatedFilename' title='$a' width='$animatedWidth'/></a>"
				else
					echo "<a href='$shapshotDir/$a/'>$a</a>"
				fi
			fi
		done
	fi

	if [[ -d $outputDir$1/$outDir/ ]]; then
		echo "<h2>OUT</h2>"
		echo "<ul>"
		for a in $(ls $outputDir$1/$outDir); do
			echo "<li> <a href='$outDir/$a' style='font-size:12pt;'>$a</a> </li>"
		done
		echo "</ul>"
	fi

	#echo '</td></tr></table>'
else
	# if [[ ${#elements[@]} -ge 2 ]]; then
	# 	echo "<h1>${elements[${#elements[@]} - 1]}</h1>"
	# else
	# 	echo "<h1>dna.example</h1>"
	# fi
	echo "<ul>"
	for a in $(ls $outputDir/$1); do
		if [[ "index.php" != $a ]]; then
			echo "<h2><a href='$a/index.php' style='font-size:14pt;'>$a</a></h2>"
			if [[ -e "$javaDir$1/$a/package-info.java" ]]; then
				#cat $javaDir$1/$a/package-info.java | grep -v package | grep -v '/\*\*' | grep -v '\*/' | sed 's/ \* //g' | sed 's/ \*//'
				awk '/\/\*\*/,/\*\//' $javaDir$1/$a/package-info.java | grep -v "/\*\*" | grep -v "\*/" | grep -v "@author" | sed 's/ \* //g' | sed 's/ \*//'
			elif [[ -e "$javaDir$1/$a.java" ]]; then
				# cat $javaDir$1/$a.java | grep "\*" | grep -v "/\*" | grep -v "\*/" | grep -v "@author" | sed 's/ \* //g' | sed 's/ \*//'
				awk '/\/\*\*/,/\*\//' $javaDir$1/$a.java | grep -v "/\*\*" | grep -v "\*/" | grep -v "@author" | sed 's/ \* //g' | sed 's/ \*//'
			fi
			echo "<?php spacer(); ?>"
		fi
	done
	echo '</ul>'
fi

echo '<?php require($pre."layout/footer.php"); ?>';