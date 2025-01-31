import excelJS from 'exceljs';
import fs from 'fs';
import User from './models/User';
import Task from './models/Task';
async function exportDataToExcel() {
    const users = await User.query();
    const tasks = await Task.query();

    const workbook = new excelJS.Workbook();
    const userSheet = workbook.addWorksheet('Users');
    const taskSheet = workbook.addWorksheet('Tasks');

    userSheet.columns = [
        { header: 'ID', key: 'id', width: 10 },
        { header: 'Name', key: 'name', width: 20 },
        { header: 'Email', key: 'email', width: 30 },
        { header: 'Mobile', key: 'mobile', width: 15 }
    ];
    taskSheet.columns = [
        { header: 'ID', key: 'id', width: 10 },
        { header: 'User ID', key: 'user_id', width: 10 },
        { header: 'Task Name', key: 'task_name', width: 30 },
        { header: 'Status', key: 'status', width: 15 }
    ];

    users.forEach(user => userSheet.addRow(user));
    tasks.forEach(task => taskSheet.addRow(task));

    if (!fs.existsSync('./exports')) {
        fs.mkdirSync('./exports');
    }
    await workbook.xlsx.writeFile('./exports/users_tasks.xlsx');
}

module.exports = { exportDataToExcel };
