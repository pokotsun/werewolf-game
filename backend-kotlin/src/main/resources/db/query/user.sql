-- name: GetUser :one
SELECT * FROM "user"
        WHERE user_id = $1 LIMIT 1;