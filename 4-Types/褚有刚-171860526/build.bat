@echo off
if not exist .\out\huluwa (
    javac .\huluwa\MainFrame.java -d out -encoding utf8
)
cd out
java huluwa.MainFrame