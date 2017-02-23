package model;

public class Association {
	
	// 1 User <---->  0-* Task 
	public static class Perform {
		
		public static void link(User user, Task task){
			task._setUser( user );
			user._getTasks().add( task );
		}
		
		public static void unlink(User user, Task task){
			user._getTasks().remove( task );
			task._setUser( null );
		}
	}
	
	// 0-1 Category <---->  0-* Task 
	public static class Classifies {
		
		public static void link(Category category, Task task){
			task._setCategory( category );
			category._getTasks().add( task );
		}
		
		public static void unlink(Category category, Task task){
			category._getTasks().remove( task );
			task._setCategory( null );
		}
	}
	
	// 1 User <---->  0-* Category 
	public static class Organizes {
		
		public static void link(User user, Category category){
			category._setUser( user );
			user._getCategories().add( category );
		}
		
		public static void unlink(User user, Category category){
			user._getCategories().remove( category );
			category._setUser( null );
		}
	}
}
