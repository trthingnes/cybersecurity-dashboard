// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { type QueryClient } from "@tanstack/react-query";
import { getApiDevices, getApiLogs, getApiLogsUnified, getApiOverview } from "../requests/services.gen";
import * as Common from "./common";
export const prefetchUseGetApiDevices = (queryClient: QueryClient, clientOptions: Options<unknown, true> = {}) => queryClient.prefetchQuery({ queryKey: Common.UseGetApiDevicesKeyFn(clientOptions), queryFn: () => getApiDevices({ ...clientOptions }).then(response => response.data) });
export const prefetchUseGetApiLogs = (queryClient: QueryClient, clientOptions: Options<unknown, true> = {}) => queryClient.prefetchQuery({ queryKey: Common.UseGetApiLogsKeyFn(clientOptions), queryFn: () => getApiLogs({ ...clientOptions }).then(response => response.data) });
export const prefetchUseGetApiLogsUnified = (queryClient: QueryClient, clientOptions: Options<unknown, true> = {}) => queryClient.prefetchQuery({ queryKey: Common.UseGetApiLogsUnifiedKeyFn(clientOptions), queryFn: () => getApiLogsUnified({ ...clientOptions }).then(response => response.data) });
export const prefetchUseGetApiOverview = (queryClient: QueryClient, clientOptions: Options<unknown, true> = {}) => queryClient.prefetchQuery({ queryKey: Common.UseGetApiOverviewKeyFn(clientOptions), queryFn: () => getApiOverview({ ...clientOptions }).then(response => response.data) });
