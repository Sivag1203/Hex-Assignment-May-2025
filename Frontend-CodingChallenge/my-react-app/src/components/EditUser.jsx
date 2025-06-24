import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";

const EditUser = () => {
  const navigate = useNavigate();
  const { id } = useParams();

  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [gender, setGender] = useState("male");
  const [status, setStatus] = useState("active");

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const res = await axios.get(`https://gorest.co.in/public/v2/users/${id}`);
        setName(res.data.name);
        setEmail(res.data.email);
        setGender(res.data.gender);
        setStatus(res.data.status);
      } catch (error) {
        alert("Failed to fetch user data.");
      }
    };
    fetchUser();
  }, [id]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const updatedUser = { name, email, gender, status };

    try {
      await axios.put(`https://gorest.co.in/public/v2/users/${id}`, updatedUser, {
        headers: {
          Authorization: `Bearer 348e8c5099bd5543c051fde22a5498ddad7de1d19c8acebeb87059604b6765a5`,
          "Content-Type": "application/json",
        },
      });
      navigate("/");
    } catch (err) {
      alert("Failed to update user.");
    }
  };

  return (
    <div className="container mt-4">
      <h2>Edit User</h2>
      <form onSubmit={handleSubmit} className="mt-3">
        <div className="mb-3">
          <label className="form-label">Name</label>
          <input
            name="name"
            className="form-control"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Email</label>
          <input
            name="email"
            type="email"
            className="form-control"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Gender</label>
          <select
            name="gender"
            className="form-select"
            value={gender}
            onChange={(e) => setGender(e.target.value)}
          >
            <option value="male">Male</option>
            <option value="female">Female</option>
          </select>
        </div>
        <div className="mb-3">
          <label className="form-label">Status</label>
          <select
            name="status"
            className="form-select"
            value={status}
            onChange={(e) => setStatus(e.target.value)}
          >
            <option value="active">Active</option>
            <option value="inactive">Inactive</option>
          </select>
        </div>
        <button className="btn btn-primary">Update</button>
      </form>
    </div>
  );
};

export default EditUser;
