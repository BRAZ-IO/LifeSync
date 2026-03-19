import java.util.ArrayList;
import java.util.List;

public class Habit {
    private String name;
    private String description;
    private int duration; // in minutes
    private int frequency; // times per week
    private boolean completed;
    
    public Habit(String name, String description, int duration, int frequency) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.frequency = frequency;
        this.completed = false;
    }
    
    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    
    public int getFrequency() { return frequency; }
    public void setFrequency(int frequency) { this.frequency = frequency; }
    
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    
    @Override
    public String toString() {
        return String.format("Habit: %s | %s | %d min/day | %d days/week | %s", 
            name, description, duration, frequency, completed ? "✓" : "✗");
    }
}
