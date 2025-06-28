import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

const UserList = () => {
  const [users, setUsers] = useState([]);
  const [page, setPage] = useState(1);

  const loadUsers = async () => {
    const res = await axios.get("https://gorest.co.in/public/v2/users");
    setUsers(res.data);
  };

  const handleDelete = async (id) => {
    if (window.confirm("Are you sure to delete this user?")) {
      await axios.delete(`https://gorest.co.in/public/v2/users/${id}`, {
        headers: {
          Authorization: `Bearer 348e8c5099bd5543c051fde22a5498ddad7de1d19c8acebeb87059604b6765a5`,
        },
      });
      setUsers(users.filter((user) => user.id !== id));
    }
  };

  useEffect(() => {
    loadUsers();
  }, []);

  const page1Users = users.slice(0, 5);
  const page2Users = users.slice(5, 10);

  const displayedUsers = page === 1 ? page1Users : page2Users;

  return (
    <div className="container mt-4">
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h2>User Management</h2>
        <Link to="/add" className="btn btn-primary">Add User</Link>
      </div>

      <table className="table table-bordered table-hover">
        <thead className="thead-dark">
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Gender</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {displayedUsers.map((user) => (
            <tr key={user.id}>
              <td>{user.name}</td>
              <td>{user.email}</td>
              <td>{user.gender}</td>
              <td>{user.status}</td>
              <td>
                <Link to={`/edit/${user.id}`} className="btn btn-sm btn-warning me-2">Edit</Link>
                <button onClick={() => handleDelete(user.id)} className="btn btn-sm btn-danger">Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <div className="d-flex justify-content-center mt-3">
        <button
          className={`btn btn-outline-primary me-2 ${page === 1 ? "active" : ""}`}
          onClick={() => setPage(1)}
        >
          Page 1
        </button>
        <button
          className={`btn btn-outline-primary ${page === 2 ? "active" : ""}`}
          onClick={() => setPage(2)}
        >
          Page 2
        </button>
      </div>
    </div>
  );
};

export default UserList;
