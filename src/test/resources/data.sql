INSERT IGNORE INTO users
    (id, nickname, password, external_id,
    auth_type, created_at, modified_at)
VALUES
    (1, 'user1', 'password', 1,
    'KAKAO', '2024-10-10 10:10:10', '2024-10-10 10:10:10'),
    (2, 'user2', 'password', 2,
    'KAKAO', '2024-10-10 10:10:10', '2024-10-10 10:10:10'),
    (3, 'user3', 'password', 3,
    'KAKAO', '2024-10-10 10:10:10', '2024-10-10 10:10:10');
