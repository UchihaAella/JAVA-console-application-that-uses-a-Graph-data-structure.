import java.util.*;

public class SocialNetworkAnalysis {
    private Map<String, Set<String>> graph;

    public SocialNetworkAnalysis() {
        graph = new HashMap<String, Set<String>>();
    }

    // Add a user to the network
    public void addUser(String name) {
        if (!graph.containsKey(name)) {
            graph.put(name, new HashSet<String>());
        }
    }

    // Add a connection (friendship) between two users
    public void addConnection(String user1, String user2) {
        if (graph.containsKey(user1) && graph.containsKey(user2)) {
            graph.get(user1).add(user2);
            graph.get(user2).add(user1);
        } else {
            System.out.println("Error: One or both users do not exist in the network.");
        }
    }

    // Identify influential users (users with most connections)
    public List<String> getInfluentialUsers() {
        int maxConnections = 0;
        List<String> influencers = new ArrayList<String>();

        for (String user : graph.keySet()) {
            int connections = graph.get(user).size();
            if (connections > maxConnections) {
                maxConnections = connections;
                influencers.clear();
                influencers.add(user);
            } else if (connections == maxConnections) {
                influencers.add(user);
            }
        }
        return influencers;
    }

    // Display the network
    public void displayNetwork() {
        for (String user : graph.keySet()) {
            System.out.println(user + ": " + graph.get(user));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SocialNetworkAnalysis network = new SocialNetworkAnalysis();

        System.out.println("Welcome to the Social Network Analysis App!");

        int command;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add User");
            System.out.println("2. Add Connection");
            System.out.println("3. View Influential Users");
            System.out.println("4. Display Network");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            command = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (command) {
                case 1:
                    System.out.print("Enter user's name: ");
                    String user = scanner.nextLine();
                    network.addUser(user);
                    System.out.println("User added.");
                    break;
                case 2:
                    System.out.print("Enter first user's name: ");
                    String user1 = scanner.nextLine();
                    System.out.print("Enter second user's name: ");
                    String user2 = scanner.nextLine();
                    network.addConnection(user1, user2);
                    System.out.println("Connection added.");
                    break;
                case 3:
                    List<String> influencers = network.getInfluentialUsers();
                    System.out.println("Influential Users: " + influencers);
                    break;
                case 4:
                    network.displayNetwork();
                    break;
                case 5:
                    System.out.println("Exiting the app. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (command != 5);

        scanner.close();
    }
}
