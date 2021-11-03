import mysql from 'mysql2/promise';
const pool = mysql.createPool({
  host: 'localhost',
  user: 'dcsuibian',
  password: 'password',
  database: 'vgtime_imitation',
  charset: 'utf8mb4',
});
export { pool };