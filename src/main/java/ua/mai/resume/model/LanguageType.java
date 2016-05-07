package ua.mai.resume.model;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public enum LanguageType {

	ALL {
      public char getDbType() {
	    return 'A';
	  }
	},

	SPOKEN{
      public char getDbType() {
	    return 'S';
	  }
	},

	WRITING{
      public char getDbType() {
	    return 'W';
	  }
	};
	
	public String getDbValue() {
		return name().toLowerCase();
	}

	public LanguageType getReverseType() {
		if (this == SPOKEN) {
			return WRITING;
		} else if (this == WRITING) {
			return SPOKEN;
		} else {
			throw new IllegalArgumentException(this+" does not have reverse type");
		}
	}

	public char getDbType() {
	  return ' ';
	}
	
}
