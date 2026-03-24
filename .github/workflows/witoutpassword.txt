import React, { useState, useEffect } from "react";
import "./App.css";

const API = window.location.hostname === "localhost"
  ? "http://localhost:8080/api/cases"
  : "https://vetri-demo-backend-ezaeapa7a3cddahr.centralindia-01.azurewebsites.net/api/cases";

function App() {
  const [cases, setCases] = useState([]);
  const [loading, setLoading] = useState(false);

  // Delete modal
  const [showDeleteModal, setShowDeleteModal] = useState(false);
  const [deleteId, setDeleteId] = useState(null);

  // Update modal
  const [showUpdateModal, setShowUpdateModal] = useState(false);
  const [updateId, setUpdateId] = useState(null);
  const [updateData, setUpdateData] = useState({
    caseTitle: "",
    description: "",
    defenderName: "",
    offenderName: "",
    caseStatus: "OPEN",
    caseType: "CRIMINAL"
  });

  const [formData, setFormData] = useState({
    caseTitle: "",
    description: "",
    defenderName: "",
    offenderName: "",
    caseStatus: "OPEN",
    caseType: "CRIMINAL"
  });

  useEffect(() => {
    fetchCases();
  }, []);

  // ✅ FETCH CASES
  const fetchCases = async () => {
    setLoading(true);
    try {
      const res = await fetch(API);
      const data = await res.json();
      setCases(data);
    } catch (err) {
      console.error(err);
      setCases([]);
    }
    setLoading(false);
  };

  // ✅ ADD CASE
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await fetch(API, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData)
      });

      if (!res.ok) {
        const text = await res.text();
        alert("❌ " + text);
        return;
      }

      await fetchCases();

      setFormData({
        caseTitle: "",
        description: "",
        defenderName: "",
        offenderName: "",
        caseStatus: "OPEN",
        caseType: "CRIMINAL"
      });

    } catch (err) {
      console.error(err);
    }
  };

  // ✅ OPEN UPDATE MODAL
  const confirmUpdate = (c) => {
    setUpdateId(c.id);
    setUpdateData({ ...c });
    setShowUpdateModal(true);
  };

  // ✅ UPDATE CASE
  const handleUpdate = async () => {
    try {
      const res = await fetch(`${API}/update/${updateId}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updateData)
      });

      const text = await res.text();

      if (!res.ok) {
        alert("❌ " + text);
        return;
      }

      alert("✅ Case Updated");
      setShowUpdateModal(false);
      await fetchCases();

    } catch (err) {
      console.error(err);
      alert("❌ Update Failed");
    }
  };

  // ✅ OPEN DELETE MODAL
  const confirmDelete = (id) => {
    setDeleteId(id);
    setShowDeleteModal(true);
  };

  // ✅ DELETE CASE
  const handleDelete = async () => {
    try {
      const res = await fetch(`${API}/delete/${deleteId}`, {
        method: "POST"
      });

      const text = await res.text();

      if (!res.ok) {
        alert("❌ " + text);
        return;
      }

      alert("🗑️ Case Deleted");
      setShowDeleteModal(false);
      await fetchCases();

    } catch (err) {
      console.error(err);
      alert("❌ Delete Failed");
    }
  };

  return (
    <div className="container">
      <h1>⚖️ Court Case System ⚖️</h1>
      <span className="badge">
        {window.location.hostname === "localhost" ? "💻 Local" : "☁️ Azure"}
      </span>

      {/* FORM */}
      <form className="glass" onSubmit={handleSubmit}>
        <input placeholder="Case Title" value={formData.caseTitle}
          onChange={(e) => setFormData({ ...formData, caseTitle: e.target.value })} required />
        <input placeholder="Description" value={formData.description}
          onChange={(e) => setFormData({ ...formData, description: e.target.value })} required />
        <input placeholder="Defender" value={formData.defenderName}
          onChange={(e) => setFormData({ ...formData, defenderName: e.target.value })} required />
        <input placeholder="Offender" value={formData.offenderName}
          onChange={(e) => setFormData({ ...formData, offenderName: e.target.value })} required />
        <select value={formData.caseStatus}
          onChange={(e) => setFormData({ ...formData, caseStatus: e.target.value })}>
          <option value="OPEN">OPEN</option>
          <option value="IN_PROGRESS">IN_PROGRESS</option>
          <option value="CLOSED">CLOSED</option>
        </select>
        <select value={formData.caseType}
          onChange={(e) => setFormData({ ...formData, caseType: e.target.value })}>
          <option value="NORMAL">NORMAL</option>
          <option value="CRIMINAL">CRIMINAL</option>
        </select>
        <button>Add Case</button>
      </form>

      {/* LOADING */}
      {loading && <p className="loading">Loading...</p>}

      {/* CASE LIST */}
      <div className="grid">
        {cases.length === 0 ? (
          <p className="empty">No Cases Found</p>
        ) : (
          cases.map((c) => (
            <div key={c.id} className="card">
              <h3>{c.caseTitle}</h3>
              <p>{c.description}</p>
              <div className="tags">
                <span>{c.caseStatus}</span>
                <span>{c.caseType}</span>
              </div>
              <p><b>{c.defenderName}</b> vs <b>{c.offenderName}</b></p>
              <div className="modal-btns">
                <button className="delete" onClick={() => confirmDelete(c.id)}>Delete</button>
                <button className="update" onClick={() => confirmUpdate(c)}>Update</button>
              </div>
            </div>
          ))
        )}
      </div>

      {/* DELETE MODAL */}
      {showDeleteModal && (
        <div className="modal">
          <div className="modal-box">
            <h3>Delete this case?</h3>
            <div className="modal-btns">
              <button onClick={handleDelete}>Confirm</button>
              <button onClick={() => setShowDeleteModal(false)}>Cancel</button>
            </div>
          </div>
        </div>
      )}

      {/* UPDATE MODAL */}
      {showUpdateModal && (
        <div className="modal">
          <div className="modal-box">
            <h3>✏️ Update Case</h3>
            <input value={updateData.caseTitle}
              onChange={(e) => setUpdateData({ ...updateData, caseTitle: e.target.value })} />
            <input value={updateData.description}
              onChange={(e) => setUpdateData({ ...updateData, description: e.target.value })} />
            <input value={updateData.defenderName}
              onChange={(e) => setUpdateData({ ...updateData, defenderName: e.target.value })} />
            <input value={updateData.offenderName}
              onChange={(e) => setUpdateData({ ...updateData, offenderName: e.target.value })} />
            <select value={updateData.caseStatus}
              onChange={(e) => setUpdateData({ ...updateData, caseStatus: e.target.value })}>
              <option value="OPEN">OPEN</option>
              <option value="IN_PROGRESS">IN_PROGRESS</option>
              <option value="CLOSED">CLOSED</option>
            </select>
            <select value={updateData.caseType}
              onChange={(e) => setUpdateData({ ...updateData, caseType: e.target.value })}>
              <option value="NORMAL">NORMAL</option>
              <option value="CRIMINAL">CRIMINAL</option>
            </select>
            <div className="modal-btns">
              <button onClick={handleUpdate}>Update</button>
              <button onClick={() => setShowUpdateModal(false)}>Cancel</button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default App;