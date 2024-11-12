document.addEventListener("DOMContentLoaded", function() {
    const sidebar = document.querySelector(".sidebar");
    const toggleBtn = document.getElementById("toggle-btn");
    const sidebarLinks = document.querySelectorAll(".nav-links a");
    const contentDiv = document.getElementById("content");

    // Toggle sidebar visibility on button click
    toggleBtn.addEventListener("click", function() {
        sidebar.classList.toggle("retracted");
        document.body.classList.toggle("sidebar-retracted"); // This will help adjust the layout of the page
    });

    // Add click event listeners to sidebar links
    sidebarLinks.forEach(link => {
        link.addEventListener("click", function(event) {
            event.preventDefault();  // Prevent default link behavior

            // Remove the 'active' class from all links
            sidebarLinks.forEach(link => link.classList.remove("active"));

            // Add 'active' class to the clicked link
            this.classList.add("active");

            const page = link.getAttribute("data-page");  // Get the page name
            loadPage(page);  // Call the function to load the page
        });
    });

    // Function to fetch and load page content into main content area
    function loadPage(page) {
        console.log(`Fetching content for: /${page}`); // Log which page is being requested
        fetch(`/${page}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to load page');
                }
                return response.text();
            })
            .then(html => {
                // Update the content div with the fetched HTML
                contentDiv.innerHTML = html;
            })
            .catch(error => {
                console.error('Error loading page:', error);
                contentDiv.innerHTML = "<p>Sorry, an error occurred while loading the page.</p>";
            });
    }

    // Load the default page on initial load
    loadPage('dashboard');
});
