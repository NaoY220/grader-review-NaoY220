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
    echo "Grade: 0"
    exit
fi


#3 put all relevant files into grading-area directory, ListExamples.java, TestListExamples.java, lib directory

cp student-submission/ListExamples.java TestListExamples.java grading-area
cp -r lib grading-area

#4 compile java files and check if compiled

cd grading-area

javac -cp $CPATH ListExamples.java TestListExamples.java

if [[ $? -eq 0 ]];
then
    echo "Compile Success"
else
    echo "Compile Failed"
    echo "Grade: 0"
    exit
fi

#5 grade based on junit tests

java -cp $CPATH org.junit.runner.JUnitCore TestListExamples

if [[ $? -eq 0 ]];
then
    echo "Tests Passed"
else
    echo "Tests Failed"
    echo "Grade: 0"
    exit
fi



#6



# Draw a picture/take notes on the directory structure that's set up after
# getting to this point

# Then, add here code to compile and run, and do any post-processing of the
# tests
