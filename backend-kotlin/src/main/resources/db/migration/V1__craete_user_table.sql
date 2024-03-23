CREATE TABLE IF NOT EXISTS "user"
(
    user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name text NOT NULL,
    updated_at timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP
);