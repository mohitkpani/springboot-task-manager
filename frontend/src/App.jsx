
import { useState, useEffect } from 'react'
import './App.css'

const API_URL = `${import.meta.env.VITE_API_URL || 'http://localhost:8080'}/api/tasks`

function App() {
  const [title, setTitle] = useState('')
  const [description, setDescription] = useState('')
  const [tasks, setTasks] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [editingId, setEditingId] = useState(null)

  // Load tasks from Spring Boot when the app opens
  useEffect(() => {
    fetchTasks()
  }, [])

  const fetchTasks = async () => {
    try {
      const response = await fetch(API_URL)

      if (!response.ok) {
        throw new Error('Failed to load tasks')
      }

      const data = await response.json()
      setTasks(data)
      setError('')
    } catch (err) {
      setError('Could not connect to the backend. Check Spring Boot.')
    } finally {
      setLoading(false)
    }
  }

  // Create a task in the database
  const addTask = async (event) => {
    event.preventDefault()

    if (!title.trim()) return

    try {
      const response = await fetch(API_URL, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          title: title.trim(),
          description: description.trim(),
          completed: false,
        }),
      })

      if (!response.ok) {
        throw new Error('Failed to create task')
      }

      const newTask = await response.json()

      setTasks((previousTasks) => [...previousTasks, newTask])
      setTitle('')
      setDescription('')
      setError('')
    } catch (err) {
      setError('Could not add task. Please try again.')
    }
  }

  // Update task completion in the database
  const toggleTask = async (id, completed) => {
    try {
      const response = await fetch(`${API_URL}/${id}`, {
        method: 'PATCH',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          completed: !completed,
        }),
      })

      if (!response.ok) {
        throw new Error('Failed to update task')
      }

      const updatedTask = await response.json()

      setTasks((previousTasks) =>
        previousTasks.map((task) =>
          task.id === id ? updatedTask : task
        )
      )
      setError('')
    } catch (err) {
      setError('Could not update task. Please try again.')
    }
  }

  // Delete a task from the database
  const deleteTask = async (id) => {
    try {
      const response = await fetch(`${API_URL}/${id}`, {
        method: 'DELETE',
      })

      if (!response.ok) {
        throw new Error('Failed to delete task')
      }

      setTasks((previousTasks) =>
        previousTasks.filter((task) => task.id !== id)
      )
      setError('')
    } catch (err) {
      setError('Could not delete task. Please try again.')
    }
  }

  // Edit a task from the database
  const editTask = async (task) => {
    const newTitle = prompt('Enter new title:', task.title)
    if (newTitle === null || !newTitle.trim()) return

    const newDescription = prompt(
      'Enter new description:',
      task.description || '',
    )
    if (newDescription === null) return

    try {
      const response = await fetch(`${API_URL}/${task.id}`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          title: newTitle.trim(),
          description: newDescription.trim(),
        }),
      })

      if (!response.ok) throw new Error('Update failed')

      const updatedTask = await response.json()

      setTasks((previousTasks) =>
        previousTasks.map((item) =>
          item.id === task.id ? updatedTask : item,
        ),
      )
      setError('')
    } catch {
      setError('Could not edit task. Please try again.')
    }
  }

  return (
    <div className="app">
      <header className="header">
        <div className="logo">✓</div>
        <h1>Taskly<span>.</span></h1>
        <p>A little progress every day.</p>
      </header>

      <main>
        <form className="task-form" onSubmit={addTask}>
          <h2>Create a task</h2>

          <input
            type="text"
            placeholder="What needs to be done?"
            value={title}
            onChange={(event) => setTitle(event.target.value)}
            required
          />

          <textarea
            placeholder="Add a short description..."
            rows="3"
            value={description}
            onChange={(event) => setDescription(event.target.value)}
          />

          <button type="submit" className="add-btn">
            <span>＋</span> Add Task
          </button>
        </form>

        <section className="tasks-section">
          <div className="section-header">
            <h2>My tasks</h2>
            <span className="task-count">
              {tasks.length} {tasks.length === 1 ? 'task' : 'tasks'}
            </span>
          </div>

          {loading ? (
            <p className="empty-message">Loading tasks...</p>
          ) : error && tasks.length === 0 ? (
            <p className="empty-message">{error}</p>
          ) : tasks.length === 0 ? (
            <p className="empty-message">
              No tasks yet. Add your first task!
            </p>
          ) : (
            tasks.map((task) => (
              <div className="task-card" key={task.id}>
                <button
                  type="button"
                  className={`checkbox ${task.completed ? 'checked' : ''}`}
                  onClick={() => toggleTask(task.id, task.completed)}
                  aria-label="Toggle task completion"
                >
                  {task.completed ? '✓' : ''}
                </button>

                <div className="task-info">
                  <h3 className={task.completed ? 'task-done' : ''}>
                    {task.title}
                  </h3>
                  <p>{task.description || 'No description'}</p>
                </div>

                <button
                  type="button"
                  className="icon-btn"
                  onClick={() => editTask(task)}
                  aria-label="Edit task"
                  title="Edit task"
                >
                  ✎
                </button>
                <button
                  type="button"
                  className="icon-btn"
                  onClick={() => deleteTask(task.id)}
                  aria-label="Delete task"
                >
                  ×
                </button>
              </div>
            ))
          )}

          {error && tasks.length > 0 && (
            <p className="empty-message">{error}</p>
          )}
        </section>
      </main>

      <footer>
        Developed By <span>♥</span> Mohit Kumar Pani
      </footer>
    </div>
  )
}

export default App
