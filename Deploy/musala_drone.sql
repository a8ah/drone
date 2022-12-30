--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4 (Debian 12.4-1.pgdg100+1)
-- Dumped by pg_dump version 12.2 (Ubuntu 12.2-4)

-- Started on 2022-12-28 10:29:43 EST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 10 (class 2615 OID 988811)
-- Name: cargo; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA cargo;


ALTER SCHEMA cargo OWNER TO postgres;

--
-- TOC entry 7 (class 2615 OID 980636)
-- Name: drone; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA drone;


ALTER SCHEMA drone OWNER TO postgres;

--
-- TOC entry 11 (class 2615 OID 972426)
-- Name: medication; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA medication;


ALTER SCHEMA medication OWNER TO postgres;

--
-- TOC entry 6 (class 2615 OID 988842)
-- Name: traking; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA traking;


ALTER SCHEMA traking OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 208 (class 1259 OID 988812)
-- Name: order; Type: TABLE; Schema: cargo; Owner: postgres
--

CREATE TABLE cargo."order" (
    uuid character varying(36) NOT NULL,
    code character varying(255) NOT NULL,
    created_at timestamp(6) without time zone,
    enabled boolean NOT NULL,
    last_update timestamp(6) without time zone,
    state character varying(255),
    weigth double precision NOT NULL,
    drone_uuid character varying(36)
);


ALTER TABLE cargo."order" OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 988820)
-- Name: order_medication; Type: TABLE; Schema: cargo; Owner: postgres
--

CREATE TABLE cargo.order_medication (
    uuid character varying(36) NOT NULL,
    created_at timestamp(6) without time zone,
    enabled boolean NOT NULL,
    last_update timestamp(6) without time zone,
    quantity integer NOT NULL,
    weigth double precision NOT NULL,
    medication_uuid character varying(36) NOT NULL,
    order_uuid character varying(36) NOT NULL
);


ALTER TABLE cargo.order_medication OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 980637)
-- Name: drone; Type: TABLE; Schema: drone; Owner: postgres
--

CREATE TABLE drone.drone (
    uuid character varying(36) NOT NULL,
    battery integer NOT NULL,
    created_at timestamp(6) without time zone,
    enabled boolean NOT NULL,
    last_update timestamp(6) without time zone,
    model character varying(255),
    serial character varying(255) NOT NULL,
    state character varying(255),
    weigth double precision NOT NULL
);


ALTER TABLE drone.drone OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 972427)
-- Name: medication; Type: TABLE; Schema: medication; Owner: postgres
--

CREATE TABLE medication.medication (
    uuid character varying(36) NOT NULL,
    code character varying(255) NOT NULL,
    created_at timestamp(6) without time zone,
    enabled boolean NOT NULL,
    image character varying(255),
    last_update timestamp(6) without time zone,
    name character varying(255) NOT NULL,
    weigth double precision NOT NULL
);


ALTER TABLE medication.medication OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 988860)
-- Name: drone_battery_history; Type: TABLE; Schema: traking; Owner: postgres
--

CREATE TABLE traking.drone_battery_history (
    uuid character varying(36) NOT NULL,
    battery integer NOT NULL,
    created_at timestamp(6) without time zone,
    drone_id character varying(255),
    drone_serial character varying(255),
    enabled boolean NOT NULL,
    last_update timestamp(6) without time zone,
    state character varying(255)
);


ALTER TABLE traking.drone_battery_history OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 988843)
-- Name: order_history; Type: TABLE; Schema: traking; Owner: postgres
--

CREATE TABLE traking.order_history (
    uuid character varying(36) NOT NULL,
    created_at timestamp(6) without time zone,
    enabled boolean NOT NULL,
    last_update timestamp(6) without time zone,
    state character varying(255),
    drone_uuid character varying(36),
    order_uuid character varying(36)
);


ALTER TABLE traking.order_history OWNER TO postgres;

--
-- TOC entry 2954 (class 0 OID 988812)
-- Dependencies: 208
-- Data for Name: order; Type: TABLE DATA; Schema: cargo; Owner: postgres
--

COPY cargo."order" (uuid, code, created_at, enabled, last_update, state, weigth, drone_uuid) FROM stdin;
\.


--
-- TOC entry 2955 (class 0 OID 988820)
-- Dependencies: 209
-- Data for Name: order_medication; Type: TABLE DATA; Schema: cargo; Owner: postgres
--

COPY cargo.order_medication (uuid, created_at, enabled, last_update, quantity, weigth, medication_uuid, order_uuid) FROM stdin;
\.


--
-- TOC entry 2953 (class 0 OID 980637)
-- Dependencies: 207
-- Data for Name: drone; Type: TABLE DATA; Schema: drone; Owner: postgres
--

COPY drone.drone (uuid, battery, created_at, enabled, last_update, model, serial, state, weigth) FROM stdin;
a2276132-ec96-4e0d-95eb-d21b0547d507	0	2022-12-27 07:16:54.241798	t	2022-12-27 07:16:54.241791	LIGHTWEIGHT	0002	IDLE	25.3
6f34fa19-a289-4d1a-9834-c94ec71606a9	100	2022-12-27 07:17:15.102014	t	2022-12-27 13:56:23.153387	CRUISERWEIGHT	0001	IDLE	400
\.


--
-- TOC entry 2952 (class 0 OID 972427)
-- Dependencies: 206
-- Data for Name: medication; Type: TABLE DATA; Schema: medication; Owner: postgres
--

COPY medication.medication (uuid, code, created_at, enabled, image, last_update, name, weigth) FROM stdin;
1a943a17-0734-4d88-8a84-420c6b69886b	MED_1	2022-12-26 14:18:43.99088	t	image1	2022-12-26 14:18:43.990867	Medication_1	22.3
33a2d9b4-3df3-443f-a1fc-af4b629ec3a9	MED_2	2022-12-26 15:24:57.896151	t	image2	2022-12-26 16:20:55.7984	Medication_2	40.7
\.


--
-- TOC entry 2957 (class 0 OID 988860)
-- Dependencies: 211
-- Data for Name: drone_battery_history; Type: TABLE DATA; Schema: traking; Owner: postgres
--

COPY traking.drone_battery_history (uuid, battery, created_at, drone_id, drone_serial, enabled, last_update, state) FROM stdin;
\.


--
-- TOC entry 2956 (class 0 OID 988843)
-- Dependencies: 210
-- Data for Name: order_history; Type: TABLE DATA; Schema: traking; Owner: postgres
--

COPY traking.order_history (uuid, created_at, enabled, last_update, state, drone_uuid, order_uuid) FROM stdin;
\.


--
-- TOC entry 2816 (class 2606 OID 988824)
-- Name: order_medication order_medication_pkey; Type: CONSTRAINT; Schema: cargo; Owner: postgres
--

ALTER TABLE ONLY cargo.order_medication
    ADD CONSTRAINT order_medication_pkey PRIMARY KEY (uuid);


--
-- TOC entry 2812 (class 2606 OID 988819)
-- Name: order order_pkey; Type: CONSTRAINT; Schema: cargo; Owner: postgres
--

ALTER TABLE ONLY cargo."order"
    ADD CONSTRAINT order_pkey PRIMARY KEY (uuid);


--
-- TOC entry 2814 (class 2606 OID 988826)
-- Name: order uk_28dgdc5siorptevhwl566i27v; Type: CONSTRAINT; Schema: cargo; Owner: postgres
--

ALTER TABLE ONLY cargo."order"
    ADD CONSTRAINT uk_28dgdc5siorptevhwl566i27v UNIQUE (code);


--
-- TOC entry 2808 (class 2606 OID 980644)
-- Name: drone drone_pkey; Type: CONSTRAINT; Schema: drone; Owner: postgres
--

ALTER TABLE ONLY drone.drone
    ADD CONSTRAINT drone_pkey PRIMARY KEY (uuid);


--
-- TOC entry 2810 (class 2606 OID 980646)
-- Name: drone ukmg002; Type: CONSTRAINT; Schema: drone; Owner: postgres
--

ALTER TABLE ONLY drone.drone
    ADD CONSTRAINT ukmg002 UNIQUE (serial);


--
-- TOC entry 2804 (class 2606 OID 972434)
-- Name: medication medication_pkey; Type: CONSTRAINT; Schema: medication; Owner: postgres
--

ALTER TABLE ONLY medication.medication
    ADD CONSTRAINT medication_pkey PRIMARY KEY (uuid);


--
-- TOC entry 2806 (class 2606 OID 980635)
-- Name: medication ukmg001; Type: CONSTRAINT; Schema: medication; Owner: postgres
--

ALTER TABLE ONLY medication.medication
    ADD CONSTRAINT ukmg001 UNIQUE (code);


--
-- TOC entry 2820 (class 2606 OID 988867)
-- Name: drone_battery_history drone_battery_history_pkey; Type: CONSTRAINT; Schema: traking; Owner: postgres
--

ALTER TABLE ONLY traking.drone_battery_history
    ADD CONSTRAINT drone_battery_history_pkey PRIMARY KEY (uuid);


--
-- TOC entry 2818 (class 2606 OID 988847)
-- Name: order_history order_history_pkey; Type: CONSTRAINT; Schema: traking; Owner: postgres
--

ALTER TABLE ONLY traking.order_history
    ADD CONSTRAINT order_history_pkey PRIMARY KEY (uuid);


--
-- TOC entry 2823 (class 2606 OID 988832)
-- Name: order_medication fk1v7jewujoa2bm8vysd07lr0pm; Type: FK CONSTRAINT; Schema: cargo; Owner: postgres
--

ALTER TABLE ONLY cargo.order_medication
    ADD CONSTRAINT fk1v7jewujoa2bm8vysd07lr0pm FOREIGN KEY (order_uuid) REFERENCES cargo."order"(uuid);


--
-- TOC entry 2822 (class 2606 OID 988827)
-- Name: order_medication fkc7m0pi7uqnfn7nc43hs6xhjcb; Type: FK CONSTRAINT; Schema: cargo; Owner: postgres
--

ALTER TABLE ONLY cargo.order_medication
    ADD CONSTRAINT fkc7m0pi7uqnfn7nc43hs6xhjcb FOREIGN KEY (medication_uuid) REFERENCES medication.medication(uuid);


--
-- TOC entry 2821 (class 2606 OID 988837)
-- Name: order fknd21nmew61qq7ooe42vmtg85h; Type: FK CONSTRAINT; Schema: cargo; Owner: postgres
--

ALTER TABLE ONLY cargo."order"
    ADD CONSTRAINT fknd21nmew61qq7ooe42vmtg85h FOREIGN KEY (drone_uuid) REFERENCES drone.drone(uuid);


--
-- TOC entry 2825 (class 2606 OID 988853)
-- Name: order_history fkcngfdnmq1wb5ttf3kjidksg9s; Type: FK CONSTRAINT; Schema: traking; Owner: postgres
--

ALTER TABLE ONLY traking.order_history
    ADD CONSTRAINT fkcngfdnmq1wb5ttf3kjidksg9s FOREIGN KEY (order_uuid) REFERENCES cargo."order"(uuid);


--
-- TOC entry 2824 (class 2606 OID 988848)
-- Name: order_history fkdufy8beud84an3lwphnultjdt; Type: FK CONSTRAINT; Schema: traking; Owner: postgres
--

ALTER TABLE ONLY traking.order_history
    ADD CONSTRAINT fkdufy8beud84an3lwphnultjdt FOREIGN KEY (drone_uuid) REFERENCES drone.drone(uuid);


-- Completed on 2022-12-28 10:29:43 EST

--
-- PostgreSQL database dump complete
--

