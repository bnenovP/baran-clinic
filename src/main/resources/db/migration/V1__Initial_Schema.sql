CREATE TABLE IF NOT EXISTS owners (
    owner_id UUID PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    city VARCHAR(100),
    street VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS dogs (
    dog_id UUID PRIMARY KEY,
    microchip_id VARCHAR(50) UNIQUE,
    dog_name VARCHAR(50) NOT NULL,
    breed VARCHAR(50),
    birth_date DATE,
    weight DOUBLE PRECISION NOT NULL,
    owner_id UUID REFERENCES owners(owner_id)
);

CREATE TABLE IF NOT EXISTS providers (
    provider_id UUID PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    license_number VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS medical_records (
    medical_record_id UUID PRIMARY KEY,
    date DATE NOT NULL,
    dog_id UUID NOT NULL REFERENCES dogs(dog_id),
    provider_id UUID NOT NULL REFERENCES providers(provider_id),
    entry_type VARCHAR(50) NOT NULL,
    notes TEXT,
    CONSTRAINT chk_entry_type CHECK (entry_type IN ('VACCINATION', 'SURGERY', 'CHECKUP', 'MEDICATION'))
);
