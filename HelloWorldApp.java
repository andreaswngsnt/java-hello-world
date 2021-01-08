// Import Method class
import java.lang.reflect.Method;
// Import Field class
import java.lang.reflect.Field;
// Import Scanner class (for input)
import java.util.Scanner;
// Array methods
import java.util.Arrays;

class App 
{
    private static Scanner scanner = null;

    public static void main(String args[]) 
    {
        scanner = new Scanner(System.in);

        System.out.println("HELLO WORLD");
        System.out.println("===========");
        printArgs(args);
        System.out.println();

        Functions.fizzbuzz();
        System.out.println();

        printArray();
        System.out.println();

        testOOP();
        System.out.println();
        
        testOverload();
        System.out.println();

        testInheritance();
        System.out.println();

        testInterface();
        System.out.println();

        testInput();

        scanner.close();
    }

    private static void printArray()
    {
        // Simple array declaration
        int[] sampleArray = {3, 5, 3, 2 ,5};
        for (int i = 0; i < sampleArray.length; i++) {
            System.out.println(sampleArray[i]);
        }

        // See default values in arrays
        int[] sampleArray2;
        sampleArray2 = new int[10];
        System.out.println(Arrays.toString(sampleArray2));

        // Check equality
        System.out.println(
            Arrays.equals(sampleArray, sampleArray2) 
                ? "The arrays are equal." 
                : "The arrays are not equal."
        );
    }

    private static void testOOP()
    {
        // Create an object instance and run its methods
        Account account1 = new Account("Andreas Wangsanata", 12342324, 100000);
        account1.printSummary();
        System.out.println();

        // Get the class of the object
        Class c1 = account1.getClass();
        System.out.println(c1.getName());
        System.out.println();

        // Get all the methods in an array
        Method m[] = c1.getDeclaredMethods();
        for (Method method : m)
            System.out.println(method.getName());
        System.out.println();

        // Get all the fields in an array
        Field f[] = c1.getDeclaredFields();
        for (Field field : f)
            System.out.println(field.getName());
    }

    // This method prints all the arguments given
    private static void printArgs(String args[])
    {
        if (args.length > 0)
        {
            System.out.println("Arguments used: ");
            for (String arg : args)
            {
                System.out.print(arg + ' ');
            }
            System.out.print('\n');
        }
        
    }

    private static void testInput()
    {
        // Get 2 integers from user and add them
        int a, b, c;
        do
        {
            System.out.print("Please enter a positive integer (a): ");
            a = scanner.nextInt();
        } while (a < 0);
        do
        {
            System.out.print("Please enter a positive integer (b): ");
            b = scanner.nextInt();
        } while (b < 0);
        c = a + b;
        System.out.println(a + " + " + b + " = " + c);

        // Get a string from user and print them back
        String userInput;
        do
        {
            System.out.println("Please enter a string: ");
            scanner.nextLine();
            userInput = scanner.nextLine();
        } while (userInput.length() < 1);
        System.out.println("You said: \"" + userInput + "\".");
        userInput += "Wololo";
        System.out.println("I said: \"" + userInput + "\".");
    }

    private static void testOverload()
    {
        Functions.overload();
        Functions.overload(5);
        Functions.overload(2, "Asdf");
    }

    private static void testInheritance()
    {
        Dog dog = new Dog("Pug");
        Dog dog2 = new Dog(false, "Siberian Husky");
        RaceDog dog3 = new RaceDog(1000);
        RaceDog dog4 = new RaceDog(false, "Panzerhund", 100000);
        System.out.println(dog.toString());
        System.out.println(dog2.toString());
        System.out.println(dog3.toString());
        System.out.println(dog4.toString());

        // Polymorphism in action
        Dog[] dogs = new Dog[] {new Dog("Pug"), new Dog(false, "Siberian Husky"), new RaceDog(1000), new RaceDog(false, "Panzerhund", 100000)};
        for (Dog d : dogs)
        {
            System.out.println(d.toString());
            d.mystery();
        }       
    }

    private static void testInterface()
    {
        String userFoodName;
        do
        {
            System.out.print("Enter a chinese food name: ");
            userFoodName = scanner.nextLine();
        }
        while (userFoodName.length() < 1);

        ChineseFood chineseFood = new ChineseFood();
        chineseFood.create(userFoodName);
        chineseFood.eat();
    }
}

class Account
{
    public String name;
    public int accountNumber;
    private int balance;

    public Account(String name, int accountNumber, int balance)
    {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void printSummary()
    {
        System.out.println("Name: " + name);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }
}

class Functions
{
    public static void fizzbuzz()
    {
        System.out.println("Fizzbuzz");
    }

    public static void overload()
    {
        System.out.println("No parameter");
    }

    public static void overload(int a)
    {
        System.out.println("Parameters: " + a);
    }

    public static void overload(int a, String b)
    {
        System.out.println("Parameters: " + a + ", " + b);
    }
}

abstract class Animal
{
    protected boolean isAlive;

    abstract void mystery();

    public Animal()
    {
        System.out.println("Animal contructor 1");
        this.isAlive = true;
    }

    public Animal(boolean isAlive)
    {
        System.out.println("Animal contructor 2");
        this.isAlive = isAlive;
    }
}

class Dog extends Animal
{
    protected String breed;

    public Dog(String breed)
    {
        System.out.println("Dog contructor 1");
        this.breed = breed;
    }

    public Dog(boolean isAlive, String breed)
    {
        super(isAlive);
        System.out.println("Dog contructor 2");
        this.breed = breed;
    }

    @Override
    public void mystery()
    {
        System.out.println("Dog's mystery function");
    }

    @Override
    public String toString()
    {
        String aliveStr = this.isAlive ? "alive" : "dead";
        return "The dog's breed is " + this.breed + " and it is " + aliveStr + "!";
    }
}

class RaceDog extends Dog
{
    private int maxSpeed;

    public RaceDog(int maxSpeed)
    {
        super("Some fast dog");
        System.out.println("RaceDog contructor 1");
        this.maxSpeed = maxSpeed;
    }

    public RaceDog(boolean isAlive, String breed, int maxSpeed)
    {
        super(isAlive, breed);
        System.out.println("RaceDog contructor 2");
        this.maxSpeed = maxSpeed;
    }

    @Override
    public void mystery()
    {
        System.out.println("RaceDog's mystery function");
    }

    @Override
    public String toString()
    {
        String aliveStr = this.isAlive ? "alive" : "dead";
        return "The dog's breed is " + this.breed + " , it's max speed is " + this.maxSpeed + " and it is " + aliveStr + "!";
    }
}

interface Food
{
    void eat();
    void create(String name);
}

class ChineseFood implements Food
{
    public String name;
    
    @Override
    public void eat()
    {
        System.out.println(this.name + " was eaten!");
    }

    @Override
    public void create(String name)
    {
        this.name = name;
        System.out.println(this.name + " was created!");
    }
}