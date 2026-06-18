$ErrorActionPreference = 'Stop'

$javaHome = 'C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.1.3\jbr'
$gradleBin = 'C:\Users\rk2real\.gradle\wrapper\dists\gradle-8.2.1-bin\5hap6b9n41hkg4jeh2au2pllh\gradle-8.2.1\bin'

$env:JAVA_HOME = $javaHome
$env:Path = "$javaHome\bin;$gradleBin;$env:Path"

gradle bootRun
