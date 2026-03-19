import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LifeSync {
    private ArrayList<Habit> habits;
    private ArrayList<String> workoutHistory;
    private String userName;
    
    public LifeSync(String userName) {
        this.userName = userName;
        this.habits = new ArrayList<>();
        this.workoutHistory = new ArrayList<>();
    }
    
    // Add a new habit to the list
    public void addHabit(Habit habit) {
        habits.add(habit);
        System.out.println("Added: " + habit.getName());
    }
    
    // Remove a habit by name
    public boolean removeHabit(String habitName) {
        for (int i = 0; i < habits.size(); i++) {
            if (habits.get(i).getName().equalsIgnoreCase(habitName)) {
                habits.remove(i);
                System.out.println("Removed: " + habitName);
                return true;
            }
        }
        System.out.println("Habit not found: " + habitName);
        return false;
    }
    
    // Mark habit as completed
    public void completeHabit(String habitName) {
        for (Habit habit : habits) {
            if (habit.getName().equalsIgnoreCase(habitName)) {
                habit.setCompleted(true);
                workoutHistory.add(habitName + " completed on " + java.time.LocalDate.now());
                System.out.println("Great job! Completed: " + habitName);
                return;
            }
        }
        System.out.println("Habit not found: " + habitName);
    }
    
    // Display all habits
    public void displayHabits() {
        System.out.println("\n=== " + userName + "'s Fitness Habits ===");
        if (habits.isEmpty()) {
            System.out.println("No habits added yet. Start building your routine!");
        } else {
            for (int i = 0; i < habits.size(); i++) {
                System.out.println((i + 1) + ". " + habits.get(i));
            }
        }
    }
    
    // Display workout history
    public void displayWorkoutHistory() {
        System.out.println("\n=== Workout History ===");
        if (workoutHistory.isEmpty()) {
            System.out.println("No workouts completed yet.");
        } else {
            for (String record : workoutHistory) {
                System.out.println("- " + record);
            }
        }
    }
    
    // Get habits by completion status
    public ArrayList<Habit> getCompletedHabits() {
        ArrayList<Habit> completed = new ArrayList<>();
        for (Habit habit : habits) {
            if (habit.isCompleted()) {
                completed.add(habit);
            }
        }
        return completed;
    }
    
    public ArrayList<Habit> getPendingHabits() {
        ArrayList<Habit> pending = new ArrayList<>();
        for (Habit habit : habits) {
            if (!habit.isCompleted()) {
                pending.add(habit);
            }
        }
        return pending;
    }
    
    // Generate personalized training plan
    public void generateTrainingPlan() {
        System.out.println("\n=== Your Personalized Training Plan ===");
        System.out.println("Based on your habits, here's your weekly plan:");
        
        int totalMinutes = 0;
        for (Habit habit : habits) {
            int weeklyMinutes = habit.getDuration() * habit.getFrequency();
            totalMinutes += weeklyMinutes;
            System.out.printf("- %s: %d minutes, %d times per week (%d min total)\n", 
                habit.getName(), habit.getDuration(), habit.getFrequency(), weeklyMinutes);
        }
        
        System.out.printf("\nTotal weekly workout time: %d minutes (%.1f hours)\n", 
            totalMinutes, totalMinutes / 60.0);
    }
    
    // Main method for demonstration
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        LifeSync lifeSync = new LifeSync(name);
        
        // Add sample habits
        lifeSync.addHabit(new Habit("Morning Run", "30-minute cardio session", 30, 3));
        lifeSync.addHabit("Push-ups", "Upper body strength training", 15, 4));
        lifeSync.addHabit("Yoga", "Flexibility and mindfulness", 45, 2));
        
        // Display menu
        while (true) {
            System.out.println("\n=== LifeSync Fitness Coach Menu ===");
            System.out.println("1. View Habits");
            System.out.println("2. Add New Habit");
            System.out.println("3. Complete Habit");
            System.out.println("4. Remove Habit");
            System.out.println("5. View Workout History");
            System.out.println("6. Generate Training Plan");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    lifeSync.displayHabits();
                    break;
                case 2:
                    System.out.print("Habit name: ");
                    String hName = scanner.nextLine();
                    System.out.print("Description: ");
                    String hDesc = scanner.nextLine();
                    System.out.print("Duration (minutes): ");
                    int hDuration = scanner.nextInt();
                    System.out.print("Frequency (times per week): ");
                    int hFrequency = scanner.nextInt();
                    scanner.nextLine();
                    lifeSync.addHabit(new Habit(hName, hDesc, hDuration, hFrequency));
                    break;
                case 3:
                    System.out.print("Enter habit name to mark complete: ");
                    String completeName = scanner.nextLine();
                    lifeSync.completeHabit(completeName);
                    break;
                case 4:
                    System.out.print("Enter habit name to remove: ");
                    String removeName = scanner.nextLine();
                    lifeSync.removeHabit(removeName);
                    break;
                case 5:
                    lifeSync.displayWorkoutHistory();
                    break;
                case 6:
                    lifeSync.generateTrainingPlan();
                    break;
                case 7:
                    System.out.println("Keep up the great work, " + name + "! Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
