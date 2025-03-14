// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { type QueryClient } from "@tanstack/react-query";
import { getApiInfo, getApiPublicIp } from "../requests/services.gen";
import * as Common from "./common";
export const prefetchUseGetApiInfo = (queryClient: QueryClient, clientOptions: Options<unknown, true> = {}) => queryClient.prefetchQuery({ queryKey: Common.UseGetApiInfoKeyFn(clientOptions), queryFn: () => getApiInfo({ ...clientOptions }).then(response => response.data) });
export const prefetchUseGetApiPublicIp = (queryClient: QueryClient, clientOptions: Options<unknown, true> = {}) => queryClient.prefetchQuery({ queryKey: Common.UseGetApiPublicIpKeyFn(clientOptions), queryFn: () => getApiPublicIp({ ...clientOptions }).then(response => response.data) });
