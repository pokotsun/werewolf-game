-- name: GetUser :one
SELECT * FROM user
        WHERE user_id = ? LIMIT 1;