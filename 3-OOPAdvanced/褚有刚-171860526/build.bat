@echo off
if not exist .\out\hw3 (
    javac .\hw3\MainFrame.java -d out -encoding utf8
)
cd out
java hw3.MainFrame