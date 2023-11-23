server {
    listen 8099 default_server;

    include /etc/nginx/includes/server_params.conf;

    location / {
        proxy_pass {{ .destination }};
        absolute_redirect off;

        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header Origin "";
        proxy_set_header Accept-Encoding "";

        sub_filter '(loc ? locproto + "//" + locip : "")' "'$http_x_ingress_path'";

        sub_filter_once off;
    }
}
