INSERT INTO tbl_post (member_no, date, clothes, sky, temp_category, review, emoji, max_temp, min_temp) VALUES
       (1, '2024-03-01', 't-shirt', 2, 1, 'Great weather today!', 1, 25, 18),
       (1, '2024-03-02', 'sweater', 5, 3, 'Chilly day but manageable.', 0, 19, 12),
       (1, '2024-03-03', 'jacket', 3, 1, 'Cloudy with mild temperature.', 2, 21, 15),
       (1, '2024-03-04', 'hoodie', 1, 6, 'Sunny and warm.', 2, 28, 20),
       (1, '2024-03-05', 'coat', 2, 1, 'Heavy rain, better stay indoors.', 0, 16, 10),
       (1, '2024-03-06', 'shorts', 4, 1, 'A bit windy but pleasant.', 1, 23, 17),
       (1, '2024-03-07', 'sweater', 0, 7, 'Freezing temperatures, bundle up!', 0, 10, 4),
       (1, '2024-03-08', 't-shirt', 2, 5, 'Partly cloudy with moderate temperature.', 1, 22, 16),
       (1, '2024-03-09', 'jacket', 3, 1, 'Overcast with intermittent showers.', 2, 18, 13),
       (1, '2024-03-10', 'hoodie', 1, 4, 'Sunny spells with a cool breeze.', 2, 20, 14),
       (1, '2024-03-11', 'coat', 2, 0, 'Torrential rain, stay dry!', 0, 15, 9),
       (1, '2024-03-12', 'shorts', 4, 3, 'Mild weather perfect for outdoor activities.', 1, 24, 18),
       (1, '2024-03-13', 'sweater', 0, 7, 'Icy conditions, be careful!', 0, 8, 3),
       (1, '2024-03-14', 't-shirt', 2, 5, 'Cloudy skies but warm temperatures.', 1, 23, 17),
       (1, '2024-03-15', 'jacket', 3, 1, 'Windy with scattered showers.', 2, 17, 11),
       (1, '2024-03-16', 'hoodie', 1, 6, 'Bright and sunny day.', 2, 27, 19),
       (1, '2024-03-17', 'coat', 2, 1, 'Heavy rainfall expected.', 0, 14, 8),
       (1, '2024-03-18', 'shorts', 4, 4, 'Pleasant temperature with clear skies.', 1, 25, 18),
       (1, '2024-03-19', 'sweater', 0, 7, 'Frigid temperatures, stay warm!', 0, 7, 2),
       (1, '2024-03-20', 't-shirt', 2, 6, 'Partly sunny with mild temperatures.', 1, 24, 16),
       (1, '2024-03-21', 'jacket', 3, 1, 'Light rain showers throughout the day.', 2, 19, 13),
       (1, '2024-03-22', 'hoodie', 1, 5, 'Sunny intervals with a cool breeze.', 2, 21, 15),
       (1, '2024-03-23', 'coat', 6, 0, 'Expect heavy rain later in the day.', 0, 13, 7),
       (1, '2024-03-24', 'shorts', 4, 2, 'Mild weather with overcast skies.', 1, 22, 16),
       (1, '2024-03-25', 'sweater', 0, 7, 'Icy conditions persist, stay safe!', 0, 6, 1),
       (1, '2024-03-26', 't-shirt', 2, 0, 'Comfortable weather, neither too hot nor too cold.', 1, 23, 17),
       (1, '2024-03-27', 'jacket', 3, 6, 'Sunny and warm throughout the day.', 2, 26, 18),
       (1, '2024-03-28', 'hoodie', 2, 1, 'Heavy rain expected, prepare accordingly.', 2, 16, 11),
       (1, '2024-03-29', 'coat', 6, 3, 'Intermittent rain with moderate temperatures.', 0, 18, 12),
       (1, '2024-03-30', 'shorts', 4, 1, 'A breezy but pleasant day.', 1, 24, 17);







INSERT INTO tbl_today (location_name, locationx, locationy, min_temp, max_temp, temperature, feels_like, sky_category, rain)
VALUES
    ('서울특별시 성북구', 0, 0, 10.0, 20.0,
     '{23.5, 22.8, 21.3, 20.9, 19.5, 18.7, 18.0, 17.2, 16.5, 15.8, 15.0, 14.3, 13.5, 13.0, 12.5, 11.8, 11.0, 10.5, 10.0, 9.5, 9.0, 8.5, 8.0, 7.5}',
     '{24.0, 23.2, 22.0, 21.5, 20.3, 19.6, 19.0, 18.2, 17.6, 16.9, 16.0, 15.5, 14.7, 14.2, 13.8, 13.0, 12.5, 12.0, 11.5, 11.0, 10.5, 10.0, 9.5, 9.0}',
     '{1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3}',
     '{0.5, 0.8, 1.2, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5, 6.0, 6.5, 7.0, 7.5, 8.0, 8.5, 9.0, 9.5, 10.0, 10.5, 11.0, 11.5}');