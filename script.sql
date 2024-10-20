create database RetoConjunto;

use RetoConjunto;

create table User(
                     id int auto_increment primary key,
                     user_name varchar(50) unique,
                     password text
);

create table Movies(
                       id int auto_increment primary key,
                       title varchar(155),
                       genre varchar(50),
                       year int,
                       description text,
                       director varchar(50)
);

create table Copies(
                       copy_id int auto_increment primary key,
                       movie_id int,
                       user_id int,
                       status ENUM('Excellent', 'Good', 'Bad') not null,
                       format ENUM('DVD', 'BluRay') NOT NULL,
                       foreign key (movie_id) references Movies(id),
                       foreign key (user_id) references User(id)
);

insert into Movies (title, genre, year, description, director)
values
    ('The Shawshank Redemption', 'Drama', 1994, 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.', 'Frank Darabont'),
    ('The Godfather', 'Crime', 1972, 'An organized crime dynasty\'s aging patriarch transfers control of his clandestine empire to his reluctant son.', 'Francis Ford Coppola'),
    ('The Dark Knight', 'Action', 2008, 'When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham.', 'Christopher Nolan'),
    ('Pulp Fiction', 'Crime', 1994, 'The lives of two mob hitmen, a boxer, a gangster\'s wife, and a pair of diner bandits intertwine in four tales of violence and redemption.', 'Quentin Tarantino'),
    ('The Lord of the Rings: The Return of the King', 'Fantasy', 2003, 'Gandalf and Aragorn lead the World of Men against Sauron\'s army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.', 'Peter Jackson'),
    ('Inception', 'Science Fiction', 2010, 'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.', 'Christopher Nolan'),
    ('Fight Club', 'Drama', 1999, 'An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into something much, much more.', 'David Fincher'),
    ('Forrest Gump', 'Drama', 1994, 'The presidencies of Kennedy and Johnson, the Vietnam War, and other historical events unfold through the perspective of an Alabama man with an IQ of 75.', 'Robert Zemeckis'),
    ('The Matrix', 'Science Fiction', 1999, 'A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.', 'Lana Wachowski, Lilly Wachowski'),
    ('Goodfellas', 'Crime', 1990, 'The story of Henry Hill and his life in the mob, covering his relationship with his wife Karen Hill and his mob partners.', 'Martin Scorsese'),
    ('The Silence of the Lambs', 'Thriller', 1991, 'A young FBI cadet must receive the help of an incarcerated and manipulative cannibal killer to catch another serial killer.', 'Jonathan Demme'),
    ('Schindler\'s List', 'Historical', 1993, 'In Nazi-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.', 'Steven Spielberg'),
    ('The Avengers', 'Action', 2012, 'Earth\'s mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army.', 'Joss Whedon'),
    ('Gladiator', 'Historical', 2000, 'A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery.', 'Ridley Scott'),
    ('Interstellar', 'Science Fiction', 2014, 'A team of explorers travel through a wormhole in space in an attempt to ensure humanity\'s survival.', 'Christopher Nolan'),
    ('Saving Private Ryan', 'War', 1998, 'Following the Normandy landings, a group of U.S. soldiers go behind enemy lines to retrieve a paratrooper whose brothers have been killed in action.', 'Steven Spielberg'),
    ('The Green Mile', 'Drama', 1999, 'The lives of guards on Death Row are affected by one of their charges: a black man accused of child murder and rape, yet who has a mysterious gift.', 'Frank Darabont'),
    ('Se7en', 'Crime', 1995, 'Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his motives.', 'David Fincher'),
    ('Django Unchained', 'Western', 2012, 'With the help of a German bounty hunter, a freed slave sets out to rescue his wife from a brutal Mississippi plantation owner.', 'Quentin Tarantino'),
    ('The Departed', 'Crime', 2006, 'An undercover cop and a mole in the police attempt to identify each other while infiltrating an Irish gang in Boston.', 'Martin Scorsese'),
    ('The Lion King', 'Animation', 1994, 'Lion prince Simba and his father are targeted by his bitter uncle, who wants to ascend the throne himself.', 'Roger Allers, Rob Minkoff'),
    ('Jurassic Park', 'Science Fiction', 1993, 'A pragmatic paleontologist visiting an almost complete theme park is tasked with protecting a couple of kids after a power failure causes the park\'s cloned dinosaurs to run loose.', 'Steven Spielberg'),
    ('Titanic', 'Romance', 1997, 'A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.', 'James Cameron'),
    ('The Social Network', 'Biography', 2010, 'Harvard student Mark Zuckerberg creates the social networking site that would become known as Facebook, but is later sued by two brothers who claimed he stole their idea.', 'David Fincher'),
    ('The Grand Budapest Hotel', 'Comedy', 2014, 'A writer encounters the owner of an aging high-class hotel and hears his life story.', 'Wes Anderson');


insert into User (user_name, password) values
    ('user1', '1234'),
    ('user2', '5678'),
    ('user3', '9101');

insert into Copies (movie_id, user_id, status, format) values
    (3,1,'Excellent','BluRay'),
    (8,2,'Good','DVD'),
    (22,1,'Bad','BluRay'),
    (25,3,'Excellent','DVD'),
    (11,1,'Bad','DVD'),
    (14,2,'Good','BluRay'),
    (8,1,'Excellent','DVD'),
    (12,3,'Good','DVD'),
    (22,1,'Excellent','BluRay'),
    (3,2,'Excellent','DVD'),
    (4,1,'Bad','BluRay'),
    (6,3,'Excellent','BluRay'),
    (9,1,'Excellent','DVD'),
    (3,3,'Good','BluRay'),
    (5,1,'Excellent','BluRay'),
    (17,2,'Excellent','DVD'),
    (22,1,'Bad','BluRay'),
    (13,2,'Excellent','DVD'),
    (3,3,'Bad','BluRay'),
    (4,3,'Good','DVD')'