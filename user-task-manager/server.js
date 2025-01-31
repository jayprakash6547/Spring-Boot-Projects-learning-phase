import express from 'express';
import { Model, knex } from 'objection';
import Knex from 'knex';
import path from 'path';
import exphbs from 'express-handlebars';
import bodyParser from 'body-parser';
import { exportDataToExcel } from './export';
import User from './models/User';
import Task from './models/Task';
const app = express();
const PORT = 3000;

const knexInstance = Knex(require('./knexfile').development);
Model.knex(knexInstance);

app.use(bodyParser.urlencoded({ extended: false }));
app.use(express.static(path.join(__dirname, 'public')));

app.engine('handlebars', exphbs.engine({ defaultLayout: 'main' }));
app.set('view engine', 'handlebars');

app.get('/', async (req, res) => {
  const users = await User.query();
  res.render('home', { users });
});

app.post('/add-user', async (req, res) => {
  const { name, email, mobile } = req.body;
  await User.query().insert({ name, email, mobile });
  res.redirect('/');
});

app.post('/add-task', async (req, res) => {
  const { user_id, task_name, status } = req.body;
  await Task.query().insert({ user_id, task_name, status });
  res.redirect('/');
});

app.get('/tasks/:userId', async (req, res) => {
  const tasks = await Task.query().where('user_id', req.params.userId);
  res.json(tasks);
});

app.get('/export', async (req, res) => {
  await exportDataToExcel();
  res.download('./exports/users_tasks.xlsx');
});

app.listen(PORT, () => console.log(`Server running on http://localhost:${PORT}`));
