public class Patient {
    private String ticketNumber;
    private int priority;
    private static int[] sequenceCounters = {0, 0, 0, 0, 0}; // Counters for each priority (1 to 5)

    public Patient(int priority) {
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 5");
        }
        this.priority = priority;
        this.ticketNumber = generateTicketNumber(priority);
    }

    private String generateTicketNumber(int priority) {
        sequenceCounters[priority - 1]++;
        return String.format("%d%03d", priority, sequenceCounters[priority - 1]);
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Paciente " + ticketNumber;
    }
}