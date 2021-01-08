import java.util.*;

public class CollectionTutorialApp {
    // Driver code
    public static void main(String args[])
    {
        arrayListDemo();
        System.out.println();

        hashMapDemo();
    }

    // ArrayList demo
    private static void arrayListDemo()
    {
        System.out.println("ArrayList test:");

        ArrayList<Integer> arrayList = new ArrayList<>();
        System.out.print("Size: " + arrayList.size() + '\n');
        System.out.print("Contents: " + arrayList.toString() + '\n');

        // Add 5 random integers
        for (int i = 0; i < 5; i++)
            arrayList.add((int)(Math.random() * (100 - 0 + 1) + 0));

        // Replace the third element
        arrayList.set(2, 100);

        System.out.print("Size: " + arrayList.size() + '\n');
        System.out.print("Contents: " + arrayList.toString() + '\n');

        // Remove an element
        arrayList.remove(2);

        System.out.print("Size: " + arrayList.size() + '\n');
        System.out.print("Contents: " + arrayList.toString() + '\n');
    }

    // HashMap demo
    private static void hashMapDemo()
    {
        System.out.println("HashMap test:");

        HashMap<String, String> hashMap = new HashMap<>();
        System.out.print("Size: " + hashMap.size() + '\n');
        System.out.print("Contents: " + hashMap.toString() + '\n');

        hashMap.put("name", "Andreas W.");
        hashMap.put("nationality", "Indonesia");
        hashMap.put("countryOfOrigin", "Indonesia");
        hashMap.put("age", "26");
        hashMap.put("major", "Undeclared");

        System.out.print("Size: " + hashMap.size() + '\n');
        System.out.print("Contents: " + hashMap.toString() + '\n');

        hashMap.put("major", "Electrical Engineering & Computer Science");
        hashMap.remove("countryOfOrigin");

        System.out.print("Size: " + hashMap.size() + '\n');
        System.out.print("Contents: " + hashMap.toString() + '\n');

        for (Map.Entry mapElement : hashMap.entrySet())
            System.out.println(mapElement.getKey() + ": " + mapElement.getValue());
    }
}
