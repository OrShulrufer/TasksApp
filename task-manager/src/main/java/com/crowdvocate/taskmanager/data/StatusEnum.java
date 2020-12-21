package com.crowdvocate.taskmanager.data;


public enum StatusEnum {
	To_do, In_Progress, Done;


        public static StatusEnum fromString(String s) {
        	 switch(s){
 	        case "To do" :
 	            return To_do;
 	        case "In progress" :
 	            return In_Progress;
 	        case "Done" :
 	            return Done;
 	        }
 	        return null;
 	    }

		 
	    public static String toString(StatusEnum sm){
	        switch(sm){
	        case To_do :
	            return "To do";
	        case In_Progress :
	            return "In progress";
	        case Done :
	            return "Done";
	        }
	        return null;
	    }

}
