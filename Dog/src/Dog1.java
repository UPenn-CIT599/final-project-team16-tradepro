
public class Dog1 {
	
	public static void main(String[] args) {
		
		String dogName = "Onyx";
		 String dogBreed = "dachshund";
		 int dogAge = 5;
		 int defaultWeight = 0;
		 double increaseWeight = .1;
		 int moreAge = 1;
		 int dogWeight = 125;

	}
	public String GetBreed(String dogBreed) {
		 return dogBreed;
		 }
	public String GetName(String dogName) {
		return dogName;
		 }
	public int GetAge(int dogAge) {
		return dogAge;
		}
	public double GetWeight(int dogWeight){
		return dogWeight;
	} 
	public void Eat(double increaseWeight, double dogWeight) {
		System.out.println(dogWeight + increaseWeight);	
		}
	public void Rename(String newName, String dogName){
		 newName = name.replace(dogName, "Fido");
		}
	public void HasBirthday(int newAge, int age, int moreAge){
		 System.out.println("Happy Birthday");
		 newAge = age + moreAge;
		 
		}

}
