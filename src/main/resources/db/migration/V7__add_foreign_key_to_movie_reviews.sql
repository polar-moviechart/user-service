ALTER TABLE movie_reviews
ADD CONSTRAINT fk_movie_reviews_user_id
FOREIGN KEY (user_id) REFERENCES users(id)
ON DELETE CASCADE
ON UPDATE CASCADE;
