PGDMP                     
    z            dsassig1    13.1    14.5     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16384    dsassig1    DATABASE     \   CREATE DATABASE dsassig1 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.utf8';
    DROP DATABASE dsassig1;
                postgres    false            �            1259    16427    devices    TABLE     �   CREATE TABLE public.devices (
    iddevices integer NOT NULL,
    address character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    mhec double precision NOT NULL,
    peopleid integer
);
    DROP TABLE public.devices;
       public         heap    postgres    false            �            1259    16433    devices_iddevices_seq    SEQUENCE     �   CREATE SEQUENCE public.devices_iddevices_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.devices_iddevices_seq;
       public          postgres    false    200            �           0    0    devices_iddevices_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.devices_iddevices_seq OWNED BY public.devices.iddevices;
          public          postgres    false    201            �            1259    16435    measurements    TABLE     �   CREATE TABLE public.measurements (
    idmeasurements integer NOT NULL,
    encons double precision NOT NULL,
    timest timestamp without time zone NOT NULL,
    deviceid integer
);
     DROP TABLE public.measurements;
       public         heap    postgres    false            �            1259    16438    measurements_idmeasurements_seq    SEQUENCE     �   CREATE SEQUENCE public.measurements_idmeasurements_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.measurements_idmeasurements_seq;
       public          postgres    false    202            �           0    0    measurements_idmeasurements_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.measurements_idmeasurements_seq OWNED BY public.measurements.idmeasurements;
          public          postgres    false    203            �            1259    16440    person    TABLE     �   CREATE TABLE public.person (
    idpeople integer NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(255) NOT NULL,
    username character varying(255) NOT NULL
);
    DROP TABLE public.person;
       public         heap    postgres    false            �            1259    16446    person_idpeople_seq    SEQUENCE     �   CREATE SEQUENCE public.person_idpeople_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.person_idpeople_seq;
       public          postgres    false    204            �           0    0    person_idpeople_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.person_idpeople_seq OWNED BY public.person.idpeople;
          public          postgres    false    205            0           2604    16448    devices iddevices    DEFAULT     v   ALTER TABLE ONLY public.devices ALTER COLUMN iddevices SET DEFAULT nextval('public.devices_iddevices_seq'::regclass);
 @   ALTER TABLE public.devices ALTER COLUMN iddevices DROP DEFAULT;
       public          postgres    false    201    200            1           2604    16449    measurements idmeasurements    DEFAULT     �   ALTER TABLE ONLY public.measurements ALTER COLUMN idmeasurements SET DEFAULT nextval('public.measurements_idmeasurements_seq'::regclass);
 J   ALTER TABLE public.measurements ALTER COLUMN idmeasurements DROP DEFAULT;
       public          postgres    false    203    202            2           2604    16450    person idpeople    DEFAULT     r   ALTER TABLE ONLY public.person ALTER COLUMN idpeople SET DEFAULT nextval('public.person_idpeople_seq'::regclass);
 >   ALTER TABLE public.person ALTER COLUMN idpeople DROP DEFAULT;
       public          postgres    false    205    204            �          0    16427    devices 
   TABLE DATA           R   COPY public.devices (iddevices, address, description, mhec, peopleid) FROM stdin;
    public          postgres    false    200   �       �          0    16435    measurements 
   TABLE DATA           P   COPY public.measurements (idmeasurements, encons, timest, deviceid) FROM stdin;
    public          postgres    false    202   )        �          0    16440    person 
   TABLE DATA           D   COPY public.person (idpeople, password, role, username) FROM stdin;
    public          postgres    false    204   �        �           0    0    devices_iddevices_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.devices_iddevices_seq', 4, true);
          public          postgres    false    201            �           0    0    measurements_idmeasurements_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.measurements_idmeasurements_seq', 6, true);
          public          postgres    false    203            �           0    0    person_idpeople_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.person_idpeople_seq', 7, true);
          public          postgres    false    205            4           2606    16452    devices devices_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.devices
    ADD CONSTRAINT devices_pkey PRIMARY KEY (iddevices);
 >   ALTER TABLE ONLY public.devices DROP CONSTRAINT devices_pkey;
       public            postgres    false    200            6           2606    16454    measurements measurements_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.measurements
    ADD CONSTRAINT measurements_pkey PRIMARY KEY (idmeasurements);
 H   ALTER TABLE ONLY public.measurements DROP CONSTRAINT measurements_pkey;
       public            postgres    false    202            8           2606    16456    person person_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (idpeople);
 <   ALTER TABLE ONLY public.person DROP CONSTRAINT person_pkey;
       public            postgres    false    204            :           2606    16458 #   person uk_n0i6d7rc1hqkjivk494g8j2qd 
   CONSTRAINT     b   ALTER TABLE ONLY public.person
    ADD CONSTRAINT uk_n0i6d7rc1hqkjivk494g8j2qd UNIQUE (username);
 M   ALTER TABLE ONLY public.person DROP CONSTRAINT uk_n0i6d7rc1hqkjivk494g8j2qd;
       public            postgres    false    204            <           2606    16459 (   measurements fk1bsciwgd3wfihkwt734uto6ad    FK CONSTRAINT     �   ALTER TABLE ONLY public.measurements
    ADD CONSTRAINT fk1bsciwgd3wfihkwt734uto6ad FOREIGN KEY (deviceid) REFERENCES public.devices(iddevices);
 R   ALTER TABLE ONLY public.measurements DROP CONSTRAINT fk1bsciwgd3wfihkwt734uto6ad;
       public          postgres    false    2868    202    200            ;           2606    16464 #   devices fkmrencqe6hjmfqtm2a0or09efd    FK CONSTRAINT     �   ALTER TABLE ONLY public.devices
    ADD CONSTRAINT fkmrencqe6hjmfqtm2a0or09efd FOREIGN KEY (peopleid) REFERENCES public.person(idpeople);
 M   ALTER TABLE ONLY public.devices DROP CONSTRAINT fkmrencqe6hjmfqtm2a0or09efd;
       public          postgres    false    204    200    2872            �   L   x�3�tL)J-N4�tI-S0�4��3�4�2�
+�č8����9���a��`�� q3N.��	X܄��hH� f��      �   G   x�Uɱ�0���"���,��t/q����lh>�n�U����ga�IN�mS��i�9����"���r      �   7  x��ϻ��0 @��|�5bKy� ��<f�$>@�~����g�
�����b�6�Nf||�Nl9��Rw�Mk��>�2M�ա	�Al�ZP�����W ��J�Y7C��!��,9t*���or�5�k�$��=�~��P؟B-�G��ߊ�U�F_+E@�("��B��vhB>��()\n�=�	+HU�ޙ���#�!��p���1��Օ�/D�qnK��݉�7�J6E�"���( �Wd^��ޒ�&�Y�XO�wm�::�lߔ�I��)�k�l{��bi��w|aW޲9�;�=1? ��琘     