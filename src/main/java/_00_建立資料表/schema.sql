DROP TABLE IF EXISTS URL_SURL_MAPPING;

CREATE TABLE `URL_SURL_MAPPING` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`short_URL`	CHAR(30) NOT NULL,
	`real_URL`	CHAR(50) NOT NULL
);

-- INSERT INTO URL_SURL_MAPPING(short_URL,real_URL) VALUES
-- ('https://1qaz25.ptt.cc/' , 'https://webcache.googleusercontent.com/search?q=cache:uUhv0CLoqFwJ:https://blog.techbridge.cc/2016/06/18/redis-introduction/+&cd=2&hl=zh-TW&ct=clnk&gl=tw');
-- INSERT INTO URL_SURL_MAPPING(short_URL,real_URL) VALUES
-- ('https://WERte2.ptt.cc/' , 'http://www.runoob.com/redis/redis-java.html');
-- INSERT INTO URL_SURL_MAPPING(short_URL,real_URL) VALUES
-- ('https://Yertfe2.ptt.cc' , 'https://www.udemy.com/courses/search/?q=c&src=ukw');


INSERT INTO URL_SURL_MAPPING(short_URL,real_URL) VALUES
('https://1qaz25.ptt.cc/' , 'https://webcache.googleusercontent.com/search?q=cache:uUhv0CLoqFwJ:https://blog.techbridge.cc/2016/06/18/redis-introduction/+&cd=2&hl=zh-TW&ct=clnk&gl=tw'),
('https://WERte2.ptt.cc/' , 'http://www.runoob.com/redis/redis-java.html'),
('https://Yertfe2.ptt.cc' , 'https://www.udemy.com/courses/search/?q=c&src=ukw');

select * from URL_SURL_MAPPING;
-- select * from URL_SURL_MAPPING;


