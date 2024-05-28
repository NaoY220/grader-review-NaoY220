CPATH='.:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar'

rm -rf student-submission
rm -rf grading-area

mkdir grading-area


#1. clone student repository
git clone $1 student-submission
echo 'Finished cloning'

#2 check that student code has ListExamples.java

if [[ -f student-submission/ListExamples.java ]]; then
    echo "ListExamples.java exists"
else 
    echo "List.Examples.java DNE"
    echo "Grade: 0/11"
    exit
fi


#3 put all relevant files into grading-area directory, ListExamples.java, TestListExamples.java, lib directory

cp student-submission/ListExamples.java TestListExamples.java grading-area
cp -r lib grading-area

#4 compile java files and check if compiled

cd grading-area
javac -cp $CPATH ListExamples.java TestListExamples.java

if [[ $? -eq 0 ]]; then
    echo "Compile Success"
else
    echo "Compile Failed"
    echo "Grade: 0"
    exit
fi

java -cp $CPATH org.junit.runner.JUnitCore TestListExamples > test-output.txt

echo "Test output can be found at list-examples-grader/grading-area/test-output.txt"

grep "FAILURES!!" test-output.txt

if [[ $? -eq 1 ]]; then
    echo "Grade: 11/11"
    exit
else
    grep "Tests run: " test-output.txt > grep-output.txt
    COMPLEMGRADE=$(cut -d " " -f 6 grep-output.txt)
    TOTAL=11
    GRADE="$(($TOTAL - $COMPLEMGRADE))"
    echo "Grade: $GRADE/11"
    exit
fi


#bash grade.sh https://github.com/ucsd-cse15l-f22/list-methods-lab3



# Draw a picture/take notes on the directory structure that's set up after
# getting to this point

# Then, add here code to compile and run, and do any post-processing of the
# tests
