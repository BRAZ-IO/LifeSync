-- Script para configurar banco de dados LifeSync no PostgreSQL
-- Execute este script como usuário postgres ou superusuário

-- 1. Criar usuário lifesync (se não existir)
DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'lifesync') THEN
        CREATE ROLE lifesync LOGIN PASSWORD 'root';
    END IF;
END
$$;

-- 2. Criar banco de dados lifesync (se não existir)
SELECT 'CREATE DATABASE lifesync'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'lifesync')\gexec

-- 3. Conceder privilégios ao usuário lifesync
GRANT ALL PRIVILEGES ON DATABASE lifesync TO lifesync;

-- 4. Conectar ao banco lifesync e conceder privilégios no schema public
\c lifesync

GRANT ALL ON SCHEMA public TO lifesync;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO lifesync;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO lifesync;

-- 5. Configurar privilégios padrão para futuras tabelas
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO lifesync;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO lifesync;

-- Verificação
SELECT 'Configuração do PostgreSQL concluída com sucesso!' as status;
