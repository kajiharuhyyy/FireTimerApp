let isWorkTime = true;
let timer = null;
let currentSet = 1;
let minutes = workMinutes;
let seconds = 0;
let isPaused = false;

function startTimer() {
  if (timer !== null) return; // 多重起動防止

  timer = setInterval(() => {
    if (isPaused) return; // 停止中は何もしない

    if (seconds === 0) {
      if (minutes === 0) {
        clearInterval(timer);
        timer = null;
        switchSession();
        return;
      } else {
        minutes--;
        seconds = 59;
      }
    } else {
      seconds--;
    }

    updateDisplay();
  }, 1000);
}

function handlePause(e) {
  e.preventDefault();
  isPaused = true;

  // タイマーは止めない（ただしカウントしないようにする）
  document.getElementById("pauseBtn").style.display = "none";
  document.getElementById("resumeBtn").style.display = "inline-block";
}

function handleResume(e) {
  e.preventDefault();
  isPaused = false;

  clearInterval(timer); // 念のため停止
  timer = null;         // タイマー変数も初期化
  startTimer();
  
  document.getElementById("resumeBtn").style.display = "none";
  document.getElementById("pauseBtn").style.display = "inline-block";
}

function handleReset(e) {
  e.preventDefault();
  clearInterval(timer);
  timer = null;
  isPaused = false;

  isWorkTime = true;
  currentSet = 1;
  minutes = workMinutes;
  seconds = 0;

  updateDisplay();
  document.getElementById("currentSet").textContent = currentSet;
  document.getElementById("sessionType").textContent = "作業中";
  document.getElementById("resumeBtn").style.display = "none";
  document.getElementById("pauseBtn").style.display = "inline-block";
}

document.addEventListener("DOMContentLoaded", () => {
  startTimer();

  document.getElementById("resetBtn").addEventListener("click", () => {
    location.href = "/";
  });

  document.getElementById("pauseBtn").addEventListener("click", () => {
    clearInterval(timer);
    isPaused = true;
    document.getElementById("pauseBtn").style.display = "none";
    document.getElementById("resumeBtn").style.display = "inline";
  });

  document.getElementById("resumeBtn").addEventListener("click", () => {
    if (isPaused) {
      isPaused = false;
      startTimer();
      document.getElementById("pauseBtn").style.display = "inline";
      document.getElementById("resumeBtn").style.display = "none";
    }
  });

  document.getElementById("logBtn").addEventListener("click", () => {
    fetch("/logs", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        subject: "ポモドーロ",
        minutes: totalSets * workMinutes
      })
    }).then(res => {
      if (res.ok) alert("学習ログを記録しました！");
    });
  });
});

function startTimer() {
  timer = setInterval(() => {
    if (!isPaused) {
      if (seconds === 0) {
        if (minutes === 0) {
          clearInterval(timer);
          switchSession();
          return;
        } else {
          minutes--;
          seconds = 59;
        }
      } else {
        seconds--;
      }

      updateDisplay();
    }
  }, 1000);
}

function updateDisplay() {
  document.getElementById("timer").textContent =
    `${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;
}

function switchSession() {
  if (isWorkTime) {
    isWorkTime = false;
    minutes = breakMinutes;
    seconds = 0;
    document.getElementById("sessionType").textContent = "休憩中";
  } else {
    currentSet++;
    if (currentSet > totalSets) {
      alert("全セット終了！");
      location.href = "/";
      return;
    }
    isWorkTime = true;
    minutes = workMinutes;
    seconds = 0;
    document.getElementById("currentSet").textContent = currentSet;
    document.getElementById("sessionType").textContent = "作業中";
  }

  startTimer();
}
