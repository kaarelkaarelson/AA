package kodu7;

import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

public class Dijkstra {

    private static HashMap<String, Integer> distancesFromStartingPoint;
    private static HashMap<String, String> visitedCities;

    public static void findShortestDistance(String startingCity, int gasLimit, String[] cityNames, int[][] distances) {
        distancesFromStartingPoint = new HashMap<>();
        visitedCities = new HashMap<>();
        List<String> unvisitedCities = new ArrayList<>();

        // first, set all distances from starting point to infinity
        for (String cityName : cityNames) {
            distancesFromStartingPoint.put(cityName, Integer.MAX_VALUE);
            unvisitedCities.add(cityName);
        }
        distancesFromStartingPoint.put(startingCity, 0);

        while (!unvisitedCities.isEmpty()) {
            String city = getClosestCity(unvisitedCities, distancesFromStartingPoint);

            // get all the distances from the current city
            for (String otherCity : cityNames) {
                int distance = distances[getIndex(cityNames, city)][getIndex(cityNames, otherCity)];
                if (distance > 0 && distance <= gasLimit) {
                    int currentDistance = distancesFromStartingPoint.get(otherCity);
                    int newDistance = distancesFromStartingPoint.get(city) + distance;
                    if (newDistance < currentDistance) {
                        distancesFromStartingPoint.put(otherCity, newDistance);
                        visitedCities.put(otherCity, city);
                    }
                }
            }
            unvisitedCities.remove(city);
        }

        displayShortestDistances(startingCity, cityNames);
    }

    private static String getClosestCity(List<String> unvisitedCities, HashMap<String, Integer> distancesFromStartingPoint) {
        String closestCity = null;
        int minDistance = Integer.MAX_VALUE;

        for (String city : unvisitedCities) {
            int currentDistance = distancesFromStartingPoint.get(city);
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                closestCity = city;
            }
        }

        return closestCity;
    }

    private static int getIndex(String[] arr, String value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    private static void displayShortestDistances(String startingCity, String[] cityNames) {
        System.out.println("Shortest distances from " + startingCity + " (km):");

        for (String cityName : cityNames) {
            if (!cityName.equals(startingCity)) {
                System.out.println(cityName + ": " + distancesFromStartingPoint.get(cityName) + "km");
            }
        }
    }

}