document.addEventListener('click', () => {
  const loginIcon = document.getElementById('login-icon');
  const popup = document.getElementById('login-popup');
  const closeBtn = document.getElementById('close-popup');

  loginIcon.addEventListener('click', () => {
    popup.classList.add('show');
  });

  closeBtn.addEventListener('click', () => {
    popup.classList.remove('show');
  });

  popup.addEventListener('click', (e) => {
    if (e.target === popup) {
      popup.classList.remove('show');
    }
  });
});
