# 뇌우 2
# 이슬비 3
# 비 5
# 눈 6
# 흐림 7
# 맑음 8
#
#     WINTER1(0),
#     WINTER2(1),
#     SPRING1(2),
#     SPRING2(3),
#     AUTUMN1(4),
#     AUTUMN2(5),
#     SUMMER1(6),
#     SUMMER2(7);
#
#  HAPPY(0), NEUTRAL(1), UNHAPPY(2);

-- 10월
INSERT INTO tbl_post (member_no, date, clothes, sky, temp_category, review, emoji, max_temp, min_temp, image1, image2, image3) VALUES
                                   (1, '2023-10-03', '체리 니트, 청바지', 8, 4, '넘 기여운 체리니트! 오늘 날씨에 딱 좋아', 0, 15, 14, 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231003-1-min.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231003-2-min.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231003-3-min.png?alt=media'),
                                   (1, '2023-10-05', '검정 니트, 슬랙스', 7, 5, '깔끔하고 무난하게, 날씨 적당', 0, 14, 12, 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231005-1-min.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231005-2-min.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231005-3-min.png?alt=media'),
                                   (1, '2023-10-10', '블랙 부츠, 블랙 숄더백', 7, 5, '답답.. 부츠는 오바', 2, 16, 13, 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231010-1-min.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231010-2-min.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231010-3-min.png?alt=media'),
                                   (1, '2023-10-14', '베이지 슬랙스, 핑크 셔츠', 8, 4, '괜춘괜춘.. 좀 더웠다.', 1, 16, 14, 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231014-1-min.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231014-2-min.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231014-2-min.png?alt=media'),
                                   (1, '2023-10-17', '아이보리 니트, 베이지 슬랙스, 검정 워커', 7, 4, '최애착장! 춥지도 덥지도 않고 좋았엉><', 0, 14, 11, 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231017-1-min.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231017-2-min.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231017-3-min.png?alt=media'),
                                   (1, '2023-10-20', '체크무늬 목도리, 꽈배기 니트, 베이지 슬랙스', 7, 4, '쬐꼼 답답... 그래도 옷은 편해', 1, 15, 12, 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231020-1-min.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231020-2-min.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231020-3-min.png?alt=media'),
                                   (1, '2023-10-23', '오프숄더 티, 와이드팬츠', 8, 5, '날씨도 좋고 옷도 이쁘고', 0, 15, 14, 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231023-1-min.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231023-2-min.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231023-3-min.png?alt=media'),
                                   (1, '2023-10-26', '원피스, 레인부츠', 5, 3, '갑지기 왠 비가.. 비올땐 레인부츠..', 1, 13, 8, 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231026-1-min.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231026-2-min.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231026-3-min.png?alt=media'),
                                   (1, '2023-10-28', '볼레로, 스키니진', 8, 4, '춥지도 덥지도 않고 딱 좋아', 0, 14, 11, 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231028-1-min.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231028-2-min.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231028-3-min.png?alt=media'),
                                   (1, '2023-10-30', '와이드팬츠, 흰 운동화', 8, 4, '흰 운동화 + 청바지 = 최고', 0, 16, 12, 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231030-1-min.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231030-2-min.png?alt=media', 'https://firebasestorage.googleapis.com/v0/b/weather-wear-a7674.appspot.com/o/231030-3-min.png?alt=media'),








-- 옷 이모지
INSERT INTO tbl_clothes (clothes_no, clothes_category, temp_category, clothes_name) VALUES
        (0, 1, 0, '기모하의'),
        (0, 1, 1, '기모하의'),
        (10, 1, 2, '청바지'),
        (10, 1, 3, '청바지'),
        (10, 1, 4, '청바지'),
        (10, 1, 5, '청바지'),
        (2, 1, 5, '면바지'),
        (1, 1, 4, '리넨하의'),
        (1, 1, 6, '리넨하의'),
        (1, 1, 7, '리넨하의'),
        (3, 1, 7, '반바지'),
        (4, 1, 5, '슬랙스'),
        (10, 1, 4, '청바지'),
        (0, 0, 1, '가죽자켓'),
        (1, 0, 0, '기모상의'),
        (2, 0, 0, '누빔옷'),
        (3, 0, 3, '니트'),
        (4, 0, 5, '롱슬리브'),
        (5, 0, 7, '리넨상의'),
        (6, 0, 4, '맨투맨'),
        (7, 0, 0, '목도리'),
        (8, 0, 7, '민소매'),
        (9, 0, 3, '바시티자켓'),
        (10, 0, 7, '반팔'),
        (11, 0, 5, '블라우스'),
        (12, 0, 2, '야상'),
        (13, 0, 4, '얇은가디건'),
        (14, 0, 4, '얇은니트'),
        (15, 0, 6, '얇은셔츠'),
        (16, 0, 1, '코트'),
        (17, 0, 2, '트렌치코트'),
        (18, 0, 0, '패딩');



INSERT INTO tbl_location(location_name, locationx, locationy, longitude, latitude) VALUES
 ('서울특별시종로구', '60', '127', '126.98', '37.57')
,  ('서울특별시중구', '60', '127', '127', '37.56')
,  ('서울특별시용산구', '60', '126', '126.97', '37.54')
,  ('서울특별시성동구', '61', '127', '127.04', '37.56')
,  ('서울특별시광진구', '62', '126', '127.08', '37.54')
,  ('서울특별시동대문구', '61', '127', '127.04', '37.57')
,  ('서울특별시중랑구', '62', '128', '127.09', '37.6')
,  ('서울특별시성북구', '61', '127', '127.02', '37.59')
,  ('서울특별시강북구', '61', '128', '127.03', '37.64')
,  ('서울특별시도봉구', '61', '129', '127.05', '37.67')
,  ('서울특별시노원구', '61', '129', '127.06', '37.65')
,  ('서울특별시은평구', '59', '127', '126.93', '37.6')
,  ('서울특별시서대문구', '59', '127', '126.94', '37.58')
,  ('서울특별시마포구', '59', '127', '126.91', '37.56')
,  ('서울특별시양천구', '58', '126', '126.87', '37.51')
,  ('서울특별시강서구', '58', '126', '126.85', '37.55')
,  ('서울특별시구로구', '58', '125', '126.89', '37.49')
,  ('서울특별시금천구', '59', '124', '126.9', '37.45')
,  ('서울특별시영등포구', '58', '126', '126.9', '37.52')
,  ('서울특별시동작구', '59', '125', '126.94', '37.51')
,  ('서울특별시관악구', '59', '125', '126.95', '37.48')
,  ('서울특별시서초구', '61', '125', '127.03', '37.48')
,  ('서울특별시강남구', '61', '126', '127.05', '37.51')
,  ('서울특별시송파구', '62', '126', '127.11', '37.51')
,  ('서울특별시강동구', '62', '126', '127.13', '37.53')
,  ('부산광역시중구', '97', '74', '129.03', '35.1')
,  ('부산광역시서구', '97', '74', '129.03', '35.09')
,  ('부산광역시동구', '98', '75', '129.06', '35.14')
,  ('부산광역시영도구', '98', '74', '129.07', '35.09')
,  ('부산광역시부산진구', '97', '75', '129.06', '35.16')
,  ('부산광역시동래구', '98', '76', '129.09', '35.2')
,  ('부산광역시남구', '98', '75', '129.09', '35.13')
,  ('부산광역시북구', '96', '76', '128.99', '35.19')
,  ('부산광역시해운대구', '99', '75', '129.17', '35.16')
,  ('부산광역시사하구', '96', '74', '128.98', '35.1')
,  ('부산광역시금정구', '98', '77', '129.09', '35.24')
,  ('부산광역시강서구', '96', '76', '128.98', '35.21')
,  ('부산광역시연제구', '98', '76', '129.08', '35.17')
,  ('부산광역시수영구', '99', '75', '129.12', '35.14')
,  ('부산광역시사상구', '96', '75', '128.99', '35.15')
,  ('부산광역시기장군', '100', '77', '129.22', '35.24')
,  ('대구광역시중구', '89', '90', '128.61', '35.87')
,  ('대구광역시동구', '90', '91', '128.64', '35.88')
,  ('대구광역시서구', '88', '90', '128.56', '35.87')
,  ('대구광역시남구', '89', '90', '128.6', '35.84')
,  ('대구광역시북구', '89', '91', '128.59', '35.88')
,  ('대구광역시수성구', '89', '90', '128.63', '35.86')
,  ('대구광역시달서구', '88', '90', '128.54', '35.83')
,  ('대구광역시달성군', '86', '88', '128.43', '35.77')
,  ('대구광역시군위군', '88', '99', '128.58', '36.24')
,  ('인천광역시중구', '54', '125', '126.62', '37.47')
,  ('인천광역시동구', '54', '125', '126.65', '37.47')
,  ('인천광역시미추홀구', '54', '124', '126.65', '37.46')
,  ('인천광역시연수구', '55', '123', '126.68', '37.41')
,  ('인천광역시남동구', '56', '124', '126.73', '37.44')
,  ('인천광역시부평구', '55', '125', '126.72', '37.5')
,  ('인천광역시계양구', '56', '126', '126.74', '37.53')
,  ('인천광역시서구', '55', '126', '126.68', '37.54')
,  ('인천광역시강화군', '51', '130', '126.49', '37.74')
,  ('인천광역시옹진군', '54', '124', '126.64', '37.44')
,  ('광주광역시동구', '60', '74', '126.93', '35.14')
,  ('광주광역시서구', '59', '74', '126.89', '35.15')
,  ('광주광역시남구', '59', '73', '126.91', '35.12')
,  ('광주광역시북구', '59', '75', '126.91', '35.17')
,  ('광주광역시광산구', '57', '74', '126.8', '35.14')
,  ('대전광역시동구', '68', '100', '127.43', '36.32')
,  ('대전광역시중구', '68', '100', '127.42', '36.32')
,  ('대전광역시서구', '67', '100', '127.39', '36.35')
,  ('대전광역시유성구', '67', '101', '127.36', '36.36')
,  ('울산광역시중구', '102', '84', '129.33', '35.57')
,  ('울산광역시남구', '102', '84', '129.33', '35.54')
,  ('울산광역시동구', '104', '83', '129.42', '35.5')
,  ('울산광역시북구', '103', '85', '129.36', '35.58')
,  ('울산광역시울주군', '101', '84', '129.3', '35.53')
,  ('세종특별자치시', '66', '103', '127.29', '36.48')
,  ('경기도수원시장안구', '60', '121', '127.01', '37.3')
,  ('경기도수원시권선구', '60', '120', '126.97', '37.25')
,  ('경기도수원시팔달구', '61', '121', '127.04', '37.28')
,  ('경기도수원시영통구', '61', '120', '127.05', '37.26')
,  ('경기도성남시수정구', '63', '124', '127.15', '37.45')
,  ('경기도성남시중원구', '63', '124', '127.14', '37.43')
,  ('경기도성남시분당구', '62', '123', '127.12', '37.38')
,  ('경기도의정부시', '61', '130', '127.04', '37.74')
,  ('경기도안양시만안구', '59', '123', '126.93', '37.38')
,  ('경기도안양시동안구', '59', '123', '126.95', '37.39')
,  ('경기도부천시원미구', '57', '125', '126.79', '37.5')
,  ('경기도부천시소사구', '57', '125', '126.8', '37.48')
,  ('경기도부천시오정구', '57', '126', '126.8', '37.53')
,  ('경기도광명시', '58', '125', '126.87', '37.48')
,  ('경기도평택시', '62', '114', '127.11', '36.99')
,  ('경기도동두천시', '61', '134', '127.06', '37.9')
,  ('경기도안산시상록구', '58', '121', '126.85', '37.3')
,  ('경기도안산시단원구', '57', '121', '126.81', '37.32')
,  ('경기도고양시덕양구', '57', '128', '126.83', '37.63')
,  ('경기도고양시일산동구', '56', '129', '126.78', '37.66')
,  ('경기도과천시', '60', '124', '126.99', '37.43')
,  ('경기도구리시', '62', '127', '127.13', '37.59')
,  ('경기도남양주시', '64', '128', '127.22', '37.63')
,  ('경기도오산시', '62', '118', '127.08', '37.15')
,  ('경기도시흥시', '57', '123', '126.81', '37.38')
,  ('경기도군포시', '59', '122', '126.94', '37.36')
,  ('경기도의왕시', '60', '122', '126.97', '37.34')
,  ('경기도하남시', '64', '126', '127.22', '37.54')
,  ('경기도용인시처인구', '64', '119', '127.2', '37.23')
,  ('경기도용인시기흥구', '62', '120', '127.12', '37.28')
,  ('경기도용인시수지구', '62', '121', '127.1', '37.32')
,  ('경기도파주시', '56', '131', '126.78', '37.76')
,  ('경기도이천시', '68', '121', '127.44', '37.28')
,  ('경기도안성시', '65', '115', '127.28', '37.01')
,  ('경기도김포시', '55', '128', '126.72', '37.61')
,  ('경기도화성시', '57', '119', '126.83', '37.2')
,  ('경기도광주시', '65', '123', '127.26', '37.41')
,  ('경기도양주시', '61', '131', '127.05', '37.78')
,  ('경기도포천시', '64', '134', '127.2', '37.89')
,  ('경기도여주시', '71', '121', '127.64', '37.3')
,  ('경기도연천군', '61', '138', '127.08', '38.09')
,  ('경기도가평군', '69', '133', '127.51', '37.83')
,  ('경기도양평군', '69', '125', '127.49', '37.49')
,  ('충청북도청주시상당구', '69', '106', '127.51', '36.58')
,  ('충청북도청주시서원구', '69', '107', '127.48', '36.64')
,  ('충청북도청주시흥덕구', '67', '106', '127.36', '36.62')
,  ('충청북도청주시청원구', '69', '107', '127.49', '36.64')
,  ('충청북도충주시', '76', '114', '127.93', '36.99')
,  ('충청북도제천시', '81', '118', '128.19', '37.13')
,  ('충청북도보은군', '73', '103', '127.73', '36.49')
,  ('충청북도옥천군', '71', '99', '127.57', '36.3')
,  ('충청북도영동군', '74', '97', '127.79', '36.17')
,  ('충청북도증평군', '71', '110', '127.58', '36.78')
,  ('충청북도진천군', '68', '111', '127.44', '36.85')
,  ('충청북도괴산군', '74', '111', '127.79', '36.81')
,  ('충청북도음성군', '72', '113', '127.69', '36.94')
,  ('충청북도단양군', '84', '115', '128.37', '36.98')
,  ('충청남도천안시동남구', '63', '110', '127.15', '36.8')
,  ('충청남도천안시서북구', '63', '112', '127.16', '36.88')
,  ('충청남도공주시', '63', '102', '127.12', '36.44')
,  ('충청남도보령시', '54', '100', '126.61', '36.33')
,  ('충청남도아산시', '60', '110', '127', '36.79')
,  ('충청남도서산시', '51', '110', '126.45', '36.78')
,  ('충청남도논산시', '62', '97', '127.1', '36.18')
,  ('충청남도계룡시', '65', '99', '127.25', '36.27')
,  ('충청남도당진시', '54', '112', '126.63', '36.89')
,  ('충청남도금산군', '69', '95', '127.49', '36.11')
,  ('충청남도부여군', '59', '99', '126.91', '36.27')
,  ('충청남도서천군', '55', '94', '126.69', '36.08')
,  ('충청남도청양군', '57', '103', '126.8', '36.46')
,  ('충청남도홍성군', '55', '106', '126.66', '36.6')
,  ('충청남도예산군', '58', '107', '126.85', '36.68')
,  ('충청남도태안군', '48', '109', '126.3', '36.74')
,  ('전라북도전주시완산구', '63', '89', '127.12', '35.81')
,  ('전라북도전주시덕진구', '63', '89', '127.14', '35.83')
,  ('전라북도군산시', '56', '92', '126.74', '35.96')
,  ('전라북도익산시', '60', '91', '126.96', '35.95')
,  ('전라북도정읍시', '58', '83', '126.86', '35.57')
,  ('전라북도남원시', '68', '80', '127.39', '35.41')
,  ('전라북도김제시', '59', '88', '126.88', '35.8')
,  ('전라북도완주군', '63', '89', '127.15', '35.84')
,  ('전라북도진안군', '68', '88', '127.43', '35.79')
,  ('전라북도무주군', '72', '93', '127.66', '36')
,  ('전라북도장수군', '70', '85', '127.52', '35.64')
,  ('전라북도임실군', '66', '84', '127.28', '35.61')
,  ('전라북도순창군', '63', '79', '127.14', '35.37')
,  ('전라북도고창군', '56', '80', '126.7', '35.43')
,  ('전라북도부안군', '56', '87', '126.74', '35.73')
,  ('전라남도목포시', '50', '67', '126.39', '34.81')
,  ('전라남도여수시', '73', '66', '127.66', '34.76')
,  ('전라남도순천시', '70', '70', '127.49', '34.95')
,  ('전라남도나주시', '56', '71', '126.71', '35.01')
,  ('전라남도광양시', '73', '70', '127.7', '34.94')
,  ('전라남도담양군', '61', '78', '126.99', '35.32')
,  ('전라남도곡성군', '66', '77', '127.29', '35.28')
,  ('전라남도구례군', '69', '75', '127.46', '35.2')
,  ('전라남도고흥군', '66', '62', '127.29', '34.61')
,  ('전라남도보성군', '62', '66', '127.08', '34.77')
,  ('전라남도화순군', '61', '72', '126.99', '35.06')
,  ('전라남도장흥군', '59', '64', '126.91', '34.68')
,  ('전라남도강진군', '57', '63', '126.77', '34.64')
,  ('전라남도해남군', '54', '61', '126.6', '34.57')
,  ('전라남도영암군', '56', '66', '126.7', '34.8')
,  ('전라남도무안군', '52', '71', '126.48', '34.99')
,  ('전라남도함평군', '52', '72', '126.52', '35.06')
,  ('전라남도영광군', '52', '77', '126.51', '35.27')
,  ('전라남도장성군', '57', '77', '126.79', '35.3')
,  ('전라남도완도군', '57', '56', '126.76', '34.31')
,  ('전라남도진도군', '48', '59', '126.27', '34.48')
,  ('전라남도신안군', '50', '66', '126.38', '34.79')
,  ('경상북도포항시남구', '102', '94', '129.36', '36.01')
,  ('경상북도포항시북구', '102', '95', '129.37', '36.04')
,  ('경상북도경주시', '100', '91', '129.23', '35.85')
,  ('경상북도김천시', '80', '96', '128.12', '36.14')
,  ('경상북도안동시', '91', '106', '128.73', '36.57')
,  ('경상북도구미시', '84', '96', '128.35', '36.12')
,  ('경상북도영주시', '89', '111', '128.63', '36.8')
,  ('경상북도영천시', '95', '93', '128.94', '35.97')
,  ('경상북도상주시', '81', '102', '128.16', '36.41')
,  ('경상북도문경시', '81', '106', '128.19', '36.58')
,  ('경상북도경산시', '91', '90', '128.74', '35.82')
,  ('경상북도의성군', '90', '101', '128.7', '36.35')
,  ('경상북도청송군', '96', '103', '129.06', '36.43')
,  ('경상북도영양군', '97', '108', '129.11', '36.66')
,  ('경상북도영덕군', '102', '103', '129.37', '36.41')
,  ('경상북도청도군', '91', '86', '128.74', '35.64')
,  ('경상북도고령군', '83', '87', '128.27', '35.72')
,  ('경상북도성주군', '83', '91', '128.29', '35.92')
,  ('경상북도칠곡군', '85', '93', '128.4', '35.99')
,  ('경상북도예천군', '86', '107', '128.46', '36.65')
,  ('경상북도봉화군', '90', '113', '128.73', '36.89')
,  ('경상북도울진군', '102', '115', '129.4', '36.99')
,  ('경상남도창원시의창구', '90', '77', '128.64', '35.25')
,  ('경상남도창원시성산구', '91', '76', '128.7', '35.2')
,  ('경상남도창원시마산합포구', '89', '76', '128.57', '35.2')
,  ('경상남도창원시마산회원구', '89', '76', '128.58', '35.22')
,  ('경상남도창원시진해구', '91', '75', '128.71', '35.13')
,  ('경상남도진주시', '81', '75', '128.11', '35.18')
,  ('경상남도통영시', '87', '68', '128.44', '34.85')
,  ('경상남도사천시', '80', '71', '128.07', '35')
,  ('경상남도김해시', '95', '77', '128.89', '35.23')
,  ('경상남도밀양시', '92', '83', '128.75', '35.5')
,  ('경상남도거제시', '90', '69', '128.62', '34.88')
,  ('경상남도양산시', '97', '79', '129.04', '35.33')
,  ('경상남도의령군', '83', '78', '128.26', '35.32')
,  ('경상남도함안군', '86', '77', '128.41', '35.27')
,  ('경상남도창녕군', '87', '83', '128.49', '35.54')
,  ('경상남도고성군', '85', '71', '128.32', '34.97')
,  ('경상남도남해군', '77', '68', '127.89', '34.83')
,  ('경상남도하동군', '74', '73', '127.75', '35.06')
,  ('경상남도산청군', '76', '80', '127.88', '35.41')
,  ('경상남도함양군', '74', '82', '127.73', '35.52')
,  ('경상남도거창군', '77', '86', '127.91', '35.68')
,  ('경상남도합천군', '81', '84', '128.17', '35.56')
,  ('제주특별자치도제주시', '53', '38', '126.53', '33.5')
,  ('제주특별자치도서귀포시', '52', '33', '126.51', '33.25')
,  ('이어도', '28', '8', '0', '0')
,  ('강원특별자치도춘천시', '73', '134', '127.73', '37.88')
,  ('강원특별자치도원주시', '76', '122', '127.92', '37.34')
,  ('강원특별자치도강릉시', '92', '131', '128.88', '37.75')
,  ('강원특별자치도동해시', '97', '127', '129.12', '37.52')
,  ('강원특별자치도태백시', '95', '119', '128.99', '37.16')
,  ('강원특별자치도속초시', '87', '141', '128.59', '38.2')
,  ('강원특별자치도삼척시', '98', '125', '129.17', '37.45')
,  ('강원특별자치도홍천군', '75', '130', '127.89', '37.69')
,  ('강원특별자치도횡성군', '77', '125', '127.99', '37.49')
,  ('강원특별자치도영월군', '86', '119', '128.46', '37.18')
,  ('강원특별자치도평창군', '84', '123', '128.39', '37.37')
,  ('강원특별자치도정선군', '89', '123', '128.66', '37.38')
,  ('강원특별자치도철원군', '65', '139', '127.32', '38.14')
,  ('강원특별자치도화천군', '72', '139', '127.71', '38.1')
,  ('강원특별자치도양구군', '77', '139', '127.99', '38.11')
,  ('강원특별자치도인제군', '80', '138', '128.17', '38.07')
,  ('강원특별자치도고성군', '85', '145', '128.47', '38.38')
,  ('강원특별자치도양양군', '88', '138', '128.62', '38.07');
