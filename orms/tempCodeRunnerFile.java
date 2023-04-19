@Override
    // public ArrayList<City> rawQueryList(String query) {
    //     ArrayList<City> cities = new ArrayList<>();

    //     try (var stmt = connection.createStatement()) {
    //         stmt.executeQuery(query);

    //         ResultSet result = stmt.executeQuery(query);
    //         while (result.next()) {
    //             var country = new CountryORM()
    //                     .rawQueryList("SELECT * FROM countries WHERE id=" + result.getInt("countryid")).get(0);

    //             cities.add(new City(result.getInt("id"),
    //                     result.getString(2),
    //                     country,
    //                     result.getString(4)));
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return cities;
    // }