package com.ntu.scse;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }

    public static class InvalidInfoException extends Exception{
        public InvalidInfoException(){
            super ("Exception: The input information is invalid!");
        }

        public InvalidInfoException(String message){
            super ("Exception: The input information is invalid! :" + message);
        }
    }
}
