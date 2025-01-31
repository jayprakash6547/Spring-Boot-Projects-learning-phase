
import { Model } from 'objection';

class Task extends Model {
  static get tableName() {
    return 'tasks';
  }
}

module.exports = Task;
