# API Prototype

Booking Hotel Search Endpoint: 

```jsx
v1/hotels/search?order_by=&adults_number=&units=&dest_id=&room_number=&checkin_date=&include_adjacency=&filter_by_currency=&locale=&dest_type=&checkout_date=
```

| **Class::attribuut** | **Is input voor API+Endpoint** | **Wordt gevuld door API+Eindpoint** | **Wordt geleverd door eindgebruiker** | **Moet worden opgeslagen in de applicatie** |
| --- | --- | --- | --- | --- |
| **Verblijf::startdatum** | Booking Hotel Search Endpoint |  | x |  |
| **Verblijf::einddatum** | Booking Hotel Search Endpoint |  | x |  |
| **Verblijfplaats::locatie** | Booking Hotel Search Endpoint |  | x |  |
| **Verblijfplaats::prijs** |  | Booking Hotel Search Endpoint |  |  |