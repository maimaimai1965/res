package ua.mai.resume.model;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public enum LanguageLevel {

	BEGINNER {
	  public char getDbLevel() {
	    return 'B';
	  }
	},
	
	ELEMENTARY {
	  public char getDbLevel() {
	    return 'E';
	  }
	},
	
	PRE_INTERMEDIATE {
	  public char getDbLevel() {
	    return 'P';
	  }
	},
	
	INTERMEDIATE {
	  public char getDbLevel() {
	    return 'I';
	  }
	},
	
	UPPER_INTERMEDIATE {
	  public char getDbLevel() {
	    return 'U';
	  }
	},
	
	ADVANCED {
	  public char getDbLevel() {
	    return 'A';
	  }
	},
	
	PROFICIENCY {
	  public char getDbLevel() {
	    return 'F';
	  }
	},;
	
	public String getDbValue(){
		return name().toLowerCase();
	}
	
	public char getDbLevel() {
	  return ' ';
	}
	
}
