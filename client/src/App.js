import { useState } from "react";
import FileUploadForm from './FileUploadForm';
import Login from "./Login";
import OperationTrackingPanel from './OperationTrackingPanel';
import "./styles.css";
function App() {


  const [isLoginView, setIsLoginView] = useState(true);
  const [uploadComplete, setUploadComplete] = useState(false);
  const [welcomeEnabled, setWelcomeEnabled] = useState(true);
  const [viewFiles, setViewFiles] = useState(false);
  const [upload, setUpload] = useState(false);

  const handleUpload = () => {
    setUploadComplete(true);
    setUpload(false);
    setWelcomeEnabled(true);
  };

  const handleViewFiles = () => {
    setViewFiles(true);
    setUpload(false);
    setWelcomeEnabled(false);
  };

  const handleUploadFiles = () => {
    setUpload(true);
    setViewFiles(false);
    setWelcomeEnabled(false);
  };

  return (
    <>
    {isLoginView && <Login isLoginView={isLoginView} setIsLoginView={setIsLoginView}/>}
    
    <center>
    {!isLoginView && welcomeEnabled ? 
      <>
          <div className="welcome-view">
        <div className="welcome-view-modal">
          <h1>Welcome {localStorage.getItem("user")} !</h1>
      <button className="button" onClick={handleUploadFiles}>Upload Files</button>
      <br />
      <button className="button" onClick={handleViewFiles}>View Files</button>
      </div>
      </div>
      </> : null}
      </center>

      {upload && <div>
        <FileUploadForm onUpload={handleUpload} setWelcomeEnabled={setWelcomeEnabled} setUpload={setUpload} />
      </div>}
      
    {viewFiles ? (
      <div>
        <OperationTrackingPanel setWelcomeEnabled={setWelcomeEnabled} setViewFiles={setViewFiles}/>
      </div>
    ) : null}
    
    </>
  );
}

export default App;

